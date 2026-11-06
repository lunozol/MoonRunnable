package com.moon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.client.main.Main;
import org.tinylog.Logger;

public class MCHook extends Minecraft {
   public MCHook(GameConfiguration gameConfiguration) {
      super(gameConfiguration);
   }

   public static void main(String[] args) {
      boolean hasVersion = false;
      boolean hasToken = false;
      if (args != null) {
         for (String arg : args) {
            if ("--version".equals(arg)) hasVersion = true;
            if ("--accessToken".equals(arg)) hasToken = true;
         }
      }

      List<String> argList = new ArrayList<>();
      if (!hasVersion) {
         argList.add("--version");
         argList.add("Moon");
      }
      if (!hasToken) {
         argList.add("--accessToken");
         argList.add("0");
      }
      if (args != null) {
         Collections.addAll(argList, args);
      }
      Main.main(argList.toArray(new String[0]));
   }

   @Override
   public void checkGLError(String string) {
      switch (string) {
         case "Post startup":
            new Moon().init();
            break;
         case "Pre startup":
            Logger.info("Starting up Moon Client");
      }

      super.checkGLError(string);
   }
}
