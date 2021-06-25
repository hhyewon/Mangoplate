package com.example.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationRegex {
    public static boolean isRegexEmail(String target) {
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }

    public static boolean isRegexPhoneNumber(String target) {
        String regex = "^01(?:0|1|[6-9])?(\\d{3}|\\d{4})?(\\d{4})$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(target);
        return matcher.find(); // 형식에 맞는 경우 true 리턴
         }

         public static boolean isRegexPassword(String target){
             String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{6,12}$";  //6~12자 영문,숫자조합
             Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
             Matcher matcher = pattern.matcher(target);
             return matcher.find();
    }

//    public static boolean isRegexId(String target){ //숫자만
//        String regex = "^[0-9]+$";
//        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(target);
//        return matcher.find();
//    }

//^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\d~!@#$%^&*()+|=]{6,12}$
//^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,12}$
//^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$

//        public static boolean isRegexId(String target) {
//            String regex = "/^-?[1-9]*$/,";
//            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
//            Matcher matcher = pattern.matcher(target);
//            return matcher.find();
//        }
}


