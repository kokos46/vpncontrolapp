package com.rps.entity.config;

import java.util.List;

public class Config {
    private List<Inbound> inbounds;
    private List<Outbound> outbounds;

    public List<Inbound> getInbounds(){ return this.inbounds; }
    public List<Outbound> getOutbounds(){ return this.outbounds; }

    public static class Inbound {
        private int port;
        private String protocol;
        private Settings settings;
        private StreamSettings streamSettings;

        public int getPort(){ return this.port; }
        public String getProtocol(){ return this.protocol; }
        public Settings getSettings(){ return this.settings; }
        public StreamSettings getStreamSettings(){ return this.streamSettings; }

        public static class Settings {
            private List<Client> clients;
            private String decryption;

            public List<Client> getClients(){ return this.clients; }
            public String getDecryption(){ return this.decryption; }

            public static class Client {
                private String id;

                public String getId(){return this.id; }
                public void setId(String id){this.id = id;}
            }
        }

        public static class StreamSettings {
            private String network;
            private String security;
            private TlsSettings tlsSettings;

            // геттеры и сеттеры

            public static class TlsSettings {
                private List<Certificate> certificates;

                // геттеры и сеттеры

                public static class Certificate {
                    private String certificateFile;
                    private String keyFile;

                    // геттеры и сеттеры
                }
            }
        }
    }

    public static class Outbound {
        private String protocol;
        public String getProtocol(){ return this.protocol; }
    }
}
