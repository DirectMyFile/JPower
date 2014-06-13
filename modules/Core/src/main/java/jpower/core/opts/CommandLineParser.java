package jpower.core.opts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses Command Line Arguments
 */
public class CommandLineParser
{

   private static final Pattern ARG_PATTERN = Pattern.compile("^(--|-)(.*)(=|\\s|)(.*)");

   public static CommandLine parse(String... args)
   {
      CommandLine cmdline = new CommandLine();

      for (String arg : args)
      {

         if (arg.startsWith("-")) // Assume it is an option
         {
            Matcher matcher = ARG_PATTERN.matcher(arg);
            if (matcher.find()) /* Assume Options */
            {
               String argname = matcher.group(2);
               String value = "true";
               if (matcher.groupCount() == 5)
               {
                  value = matcher.group(4);
               }
               cmdline.set(argname, value);
               continue;
            }
         }

         cmdline.arguments().add(arg);
      }

      return cmdline;
   }
}
