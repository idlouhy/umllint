package net.umllint.tool.cli;

import net.umllint.tool.ULContext;
import net.umllint.tool.ULToolController;
import org.apache.commons.cli.*;

public class ULCommandLineArguments {

  private Options options = new Options();

  public ULCommandLineArguments() {

    Option option;
    OptionGroup optionGroupActions = new OptionGroup();
    optionGroupActions.setRequired(true);

    //Use Case: View pattern detail
    option = OptionBuilder
        .hasArg()
        .withArgName("ID")
        .withDescription("view pattern")
        .withLongOpt("pattern")
        .create("p");
    optionGroupActions.addOption(option);


    //Use Case: List patterns
    option = OptionBuilder
        .withDescription("list patterns")
        .withLongOpt("list")
        .create("l");
    optionGroupActions.addOption(option);


    //Use Case: Run correctness analysis
    option = OptionBuilder
        .withDescription("analyze")
        .withLongOpt("analyze")
        .create("a");
    optionGroupActions.addOption(option);


    //Use Case: Update the pattern database
    option = OptionBuilder
        .withDescription("update pattern database")
        .withLongOpt("update")
        .create("u");
    optionGroupActions.addOption(option);

    //Use Case: Ignore pattern
    option = OptionBuilder
        .hasArgs(2)
        .withArgName("ID> <BOOL")
        .withDescription("enable pattern")
        .withLongOpt("enable")
        .create("e");
    optionGroupActions.addOption(option);

    /*
    //Use Case: Ignore element
    option = OptionBuilder
        .hasArgs(2)
        .withArgName("expression> <bool")
        .withDescription("ignore element")
        .withLongOpt("ignore")
        .create("i");
    optionGroupActions.addOption(option);
    */


    //Use Case: Display help
    option = OptionBuilder
        .withDescription("show help")
        .withLongOpt("help")
        .create("h");
    optionGroupActions.addOption(option);


    options.addOptionGroup(optionGroupActions);

    //Use Case: Set property file
    option = OptionBuilder
        .withArgName("FILE")
        .hasArg()
        .withDescription("use property file")
        .withLongOpt("config")
        .create("c");
    options.addOption(option);

    //Use Case: Set input file
    option = OptionBuilder
        .hasArg()
        .withArgName("FILE")
        .withDescription("input XMI")
        .withLongOpt("input")
        .create("i");
    options.addOption(option);

    //Use Case: Set pattern database file
    option = OptionBuilder
        .hasArg()
        .withArgName("FILE")
        .withDescription("pattern database")
        .withLongOpt("database")
        .create("d");
    options.addOption(option);
  }

  public ULToolController.ULMode setUp(String[] args) throws ParseException {
    CommandLineParser commandLineParser = new BasicParser();

    CommandLine commandLine = commandLineParser.parse(options, args);

    ULToolController.ULMode mode = ULToolController.ULMode.HELP;

    if (commandLine.hasOption("a")) {
      mode = ULToolController.ULMode.ANALYZE;
    }

    if (commandLine.hasOption("l")) {
      mode = ULToolController.ULMode.LIST;
    }

    if (commandLine.hasOption("p")) {
      mode = ULToolController.ULMode.PATTERN;
    }

    if (commandLine.hasOption("u")) {
      mode = ULToolController.ULMode.UPDATE;
    }

    if (commandLine.hasOption("e")) {
      mode = ULToolController.ULMode.ENABLE;
    }

    if (commandLine.hasOption("i")) {
      mode = ULToolController.ULMode.IGNORE;
    }

    if (commandLine.hasOption("c")) {
      String value = commandLine.getOptionValue("c");
      ULContext.instance().set("umllint.file.config", value);
    }

    if (commandLine.hasOption("i")) {
      String value = commandLine.getOptionValue("i");
      ULContext.instance().set("umllint.file.input", value);
    }

    if (commandLine.hasOption("d")) {
      String value = commandLine.getOptionValue("d");
      ULContext.instance().set("umllint.file.database", value);
    }

    return mode;
  }

  public void printHelp() {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("umllint-tool", options);
  }
}
