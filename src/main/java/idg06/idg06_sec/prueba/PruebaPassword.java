/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idg06.idg06_sec.prueba;

import idg06.idg06_sec.security.WebSecurityConfig;

/**
 *
 * @author DannVJ
 */
public class PruebaPassword {
    public static void main(String[] args) {
        System.out.println("123456" + WebSecurityConfig.passwordEncoder().encode("123456")); 
        System.out.println("admin" + WebSecurityConfig.passwordEncoder().encode("admin")); 
    }
}
