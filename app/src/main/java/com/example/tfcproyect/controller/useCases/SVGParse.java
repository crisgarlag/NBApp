package com.example.tfcproyect.controller.useCases;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SVGParse {

    public static SVG parseo(String urlImage){
        try {
            URL url = new URL(urlImage);
            HttpsURLConnection connection  = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            SVG svg = SVG.getFromInputStream(inputStream);
            return svg;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}
