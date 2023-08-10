/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idg06.idg06_sec.prueba;

import idg06.idg06_sec.security.WebSecurityConfig;

/**
 *
 * @author DannVJ
 
public class Prueba {
    //psvm
    public static void main(String[] args) {
        for(int i=0; i<5; i++) {
            System.err.println("pwd (123):" + WebSecurityConfig.passwordEncoder().encode("123"));
        }
    }
}
*/
