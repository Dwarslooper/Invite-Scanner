package com.ether.testsysten;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.PrivilegedActionException;
import java.text.ParseException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws Exception {

        // write your code here
        int delay;
        int maxURLs;
        boolean shouldOpenOnSuccess;

        try {
            delay = Integer.parseInt(args[0]);
            maxURLs = Integer.parseInt(args[1]);
            shouldOpenOnSuccess = Boolean.parseBoolean(args[2]);
        } catch (Exception e) {
            System.out.println("Error: Arguments incorrent! <Delay (Integer)> <MaxScans (Integer)> <ShouldOpenOnSuccess (Boolean)>");
            return;
        }

        Thread t = new Thread(() -> {
            for(int i = 0; i < maxURLs; i++) {
                String invite = new RandomString(8, ThreadLocalRandom.current(), RandomString.alphanum).nextString();
                try {
                    String url = "https://discord.com/invite/" + invite;
                    System.out.println("Scanning " + url);
                    InputStream is = new URL(url).openStream();
                    String b = new String(is.readAllBytes());

                    if(!b.contains("endsection")) {
                        System.out.println("-----------------------");
                        System.out.println("Working Invite: " + url);
                        System.out.println("-----------------------");
                        if(shouldOpenOnSuccess) openWebpage(new URL(url));
                    } else {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error while getting IRC Address from Website: ");
                    return;
                }
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
}
