/**
 * Created by wei.shen on 2015/11/11.
 */
module.exports = function(grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        concat: {
            build: {
                src:
                    ["src/main/webapp/assets/js/*.js",
                        "src/main/webapp/assets/js/controller/*.js",
                        "src/main/webapp/assets/js/service/*.js",
                        "src/main/webapp/assets/js/filter/*.js",
                        "src/main/webapp/assets/js/directive/*.js"],
                dest: "src/main/webapp/assets/dest/js/<%= pkg.name %>.js"
            }
        },
        uglify: {
            options: {
                banner: '/*! <%= pkg.name %> <%= grunt.template.today("dd-mm-yyyy") %> */\n'
            },
            bulid: {
                // 动态文件映射，
                // 当任务运行时会自动在 "lib/" 目录下查找 "**/*.js" 并构建文件映射，
                // 添加或删除文件时不需要更新 Gruntfile。
                files: [
                    {
                        "src/main/webapp/assets/dest/bin/<%= pkg.name %>.min.js": ['<%= concat.build.dest %>']
                    },
                ],
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.registerTask('default', ['concat','uglify']);
}