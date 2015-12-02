package com.fanli.scheduler.service;

import com.jcraft.jsch.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wei.shen on 2015/7/29.
 */
@Service
public class SSHService {
    public static final String USER = "hadoop";
    public static final String PASSWORD = "hadoop!2345678";
    public static final String IP = "192.168.3.227";

    private Session initSSH() throws JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(USER, IP);
        session.setPassword(PASSWORD);
        session.setConfig("userauth.gssapi-with-mic", "no");
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        return session;
    }

    private Channel makeChannel(Session session,String command) throws JSchException {
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);
        channel.connect();
        return channel;
    }

    public String runSSHCommand(String command) {
        try {
            Session session = initSSH();
            Channel channel = makeChannel(session, command);

            StringBuilder stringBuffer = new StringBuilder();
            InputStream in = channel.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String buf;
            while ((buf = reader.readLine()) != null) {
                stringBuffer.append(buf.trim()).append("\n");
            }
            channel.disconnect();
            session.disconnect();
            return stringBuffer.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isRunSSHCommandSuccessful(String command) throws JSchException, IOException {
        Session session = initSSH();
        Channel channel = makeChannel(session, command);
        InputStream in = channel.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String buf;
        while ((buf = reader.readLine()) != null) {}
        if (channel.getExitStatus() != 0 ) {
            channel.disconnect();
            session.disconnect();
            return false;
        } else {
            channel.disconnect();
            session.disconnect();
            return true;
        }
    }

    public Map<String,Object> runSSHCommandWithLogAndResult(String command) throws JSchException, IOException {
        Map<String,Object> map = new HashMap<String, Object>();
        Session session = initSSH();
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);
        InputStream in = channel.getInputStream();
        InputStream err = ((ChannelExec) channel).getErrStream();
        channel.connect();
        StringBuffer sb = new StringBuffer();
        String buf;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        while ((buf = reader.readLine()) != null) {
            sb.append(buf.trim()).append("\n");
        }
        if (channel.getExitStatus() != 0 ) {
            map.put("isSuccess", false);
            //BufferedReader reader = new BufferedReader(new InputStreamReader(err));
            //readAll(reader, sb);
        } else {
            map.put("isSuccess", true);
            //BufferedReader reader = new BufferedReader(new InputStreamReader(in));
           // readAll(reader, sb);
        }
        map.put("returnCode",channel.getExitStatus());
        map.put("log",sb.toString());
        if (channel != null) {
            channel.disconnect();
        }

        if (session != null) {
            session.disconnect();
        }
        return map;
    }

    private void readAll(BufferedReader reader,StringBuffer result) throws IOException {
        String buf;
        while ((buf = reader.readLine()) != null) {
            result.append(buf.trim()).append("\n");
        }
    }
}
