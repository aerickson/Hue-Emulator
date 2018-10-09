package com.hueemulator.emulator;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

@Command(name = "java -jar hue_emulator.jar", mixinStandardHelpOptions = true, version = "0.9")
public class CommandLine implements Callable<Void>{

    private static picocli.CommandLine cl;

//    @Option(names = { "-v", "--verbose" }, description = "Verbose mode. Helpful for troubleshooting. " +
//            "Multiple -v options increase the verbosity.")
//    private boolean[] verbose = new boolean[0];

    @Parameters(arity = "0..1", paramLabel = "CONFIG_FILE", description = "Config file to use.")
    private static String configFile = null;

    @Override
    public Void call() throws Exception {
//        System.out.println("in call()");
        new HueEmulator(configFile);
        return null;
    }

    public static void main(String[] args) {
//        System.out.println("in main()");
        picocli.CommandLine.call(new CommandLine(), args);
    }

}