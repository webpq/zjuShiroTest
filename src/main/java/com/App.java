package com;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class App {

    public static void main( String[] args ) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
    //    SecurityManager securityManager =
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken("pengqi", "123");

        try{
            subject.login(token);
        }catch(IncorrectCredentialsException e){
            System.out.println("密码错误");
        }catch (UnknownAccountException e){
            System.out.println("用户名不存在");
        }
        catch (AuthenticationException e){
            System.out.println("用户名密码错误");
        }
        System.out.println(subject.getPrincipal()+"登陆成功");
        if(subject.hasRole("admin")){
            System.out.println("管理员");
        }
        if(subject.isPermitted("hh")){
            System.out.println("允许*");
        }

       // System.out.println( "Hello World!" );
    }
}
