package br.com.hub.core.utils;

public class ConvertData {

    public ConvertData() {
        // default constructor
    }

    public static String convertDataFormat(String query) {

        String ano = query.substring(6, 10);
        String mes = query.substring(3, 5);
        String dia = query.substring(0, 2);
        String hora = query.substring(11, 13);
        String min = query.substring(14, 16);
        String seg = query.substring(17, 19);

        query = ano + "-" + mes + "-" + dia + " " + hora + ":" + min + ":" + seg;

        return query;
    }
}
