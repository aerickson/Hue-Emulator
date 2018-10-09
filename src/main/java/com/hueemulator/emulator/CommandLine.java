package com.hueemulator.emulator;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import java.util.concurrent.Callable;

@Command(name = "example", mixinStandardHelpOptions = true, version = "Picocli example 3.0")
public class CommandLine implements Callable<Void>{

    private static picocli.CommandLine cl;

    @Option(names = { "-v", "--verbose" }, description = "Verbose mode. Helpful for troubleshooting. " +
            "Multiple -v options increase the verbosity.")
    private boolean[] verbose = new boolean[0];

    @Parameters(arity = "0..1", paramLabel = "CONFIG_FILE", description = "Config file to use.")
    private static String configFile = null;

    @Override
    public Void call() throws Exception {
//        if (verbose.length > 0) {
//            System.out.println(inputFiles.length + " files to process...");
//        }
//        if (verbose.length > 1) {
//            for (File f : inputFiles) {
//                System.out.println(f.getAbsolutePath());
//            }
//        }

//        new CommandLine(args.length > 0 ? args[0] : null);
//        if (CommandLine.isVersionHelpRequested() || CommandLine.isUsageHelpRequested()){
//            // do nothing
//        }
//        else {
//            new CommandLine(configFile);
//        }
        System.out.println("in call()");
        new HueEmulator(configFile);
        return null;
    }

    public static void main(String[] args) {
        System.out.println("in main()");

        // TODO: how to store CL below into cl?
        picocli.CommandLine.call(new CommandLine(), args);
//        new CommandLine(args.length > 0 ? args[0] : null);
    }

//    public static void main(String args[]) {
//        new CommandLine(args.length > 0 ? args[0] : null);
//    }
    

}