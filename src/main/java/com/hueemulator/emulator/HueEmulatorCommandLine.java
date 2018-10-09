package com.hueemulator.emulator;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

import picocli.CommandLine.IVersionProvider;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;


@Command(name = "java -jar hue_emulator.jar", mixinStandardHelpOptions = true,
        versionProvider = HueEmulatorCommandLine.ManifestVersionProvider.class)
public class HueEmulatorCommandLine implements Callable<Void>{

    @Parameters(arity = "0..1", paramLabel = "CONFIG_FILE", description = "Config file to use.")
    private static String configFile = null;

    @Option(names = { "-p", "--port" }, paramLabel = "PORT", description = "Port to default to.")
    private static int portNumber = Constants.DEFAULT_PORT;

    @Override
    public Void call() {
        new HueEmulator(configFile, portNumber);
        return null;
    }

    public static void main(String[] args) {
        picocli.CommandLine.call(new HueEmulatorCommandLine(), args);
    }


    /**
     * {@link IVersionProvider} implementation that returns version information from the picocli-x.x.jar file's {@code /META-INF/MANIFEST.MF} file.
     */
    static class ManifestVersionProvider implements IVersionProvider {
        public String[] getVersion() throws Exception {
            Enumeration<URL> resources = HueEmulatorCommandLine.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                try {
                    Manifest manifest = new Manifest(url.openStream());
                    if (isApplicableManifest(manifest)) {
                        Attributes attr = manifest.getMainAttributes();
                        return new String[] { get(attr, "Implementation-Title") + " version \"" +
                                get(attr, "Implementation-Version") + "\"" };
                    }
                } catch (IOException ex) {
                    return new String[] { "Unable to read from " + url + ": " + ex };
                }
            }
            return new String[0];
        }

        private boolean isApplicableManifest(Manifest manifest) {
            Attributes attributes = manifest.getMainAttributes();
            return "hue-emulator".equals(get(attributes, "Implementation-Title"));
        }

        private static Object get(Attributes attributes, String key) {
            return attributes.get(new Attributes.Name(key));
        }
    }

}