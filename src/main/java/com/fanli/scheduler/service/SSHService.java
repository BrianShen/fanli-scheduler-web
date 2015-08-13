package com.fanli.scheduler.service;

import com.jcraft.jsch.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
}
