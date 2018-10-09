package com.hueemulator.emulator;

import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

@Command(name = "java -jar hue_emulator.jar", mixinStandardHelpOptions = true, version = "0.9")
public class CommandLine implements Callable<Void>{

    @Parameters(arity = "0..1", paramLabel = "CONFIG_FILE", description = "Config file to use.")
    private static String configFile = null;

    @Override
    public Void call() {
        new HueEmulator(configFile);
        return null;
    }

    public static void main(String[] args) {
        picocli.CommandLine.call(new CommandLine(), args);
    }

}