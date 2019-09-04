package org.camobiwon.boneworksbot;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;

class ResourceLoader {
    String getFileContent(String fileName) throws IOException {
        URL url = Resources.getResource(fileName);
        return Resources.toString(url, Charsets.UTF_8);
    }
}