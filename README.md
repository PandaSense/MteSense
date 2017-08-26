# Mtesense
This is lightweight testing automation framework with selenium and appium.

# Release History
  - Add MteSenseWdd support(Webdriver Dynamic Debug)
  - Add MteMonkeyRunner module, using java code to write script like using python with MonkeyRunner(Android)

# Requirements
This project based on selenium and appium tools and developed by java program. If you want to config or use it, please check with the following information.

  - JDK 1.8 or above.
  - Eclispe or Intelij IDEA or any java ide.
  - Webdriver 2.0.
  - selenium 3.0 and appium 1.6.4 or above.
  - OS include Windows, Linux or Mac.

# Installation

  1. Download source zip file into your local machine and unzip the file.
  2. Create normal java project with your java ide include Eclipse or Intelij IDEA.
  3. Copy all of source files under src folder into your loacl src foler in your jave project.
  4. Copy config and lib folders into root path for your local project.
  5. Add all of jar files into your local classpath for your project to build mtesense with your ide.
  6. Check with mtesense.properties, almost all of properties for mtesense in this file include report, driver path, screencapture path and delaytime etc.
  7. For MteMonkeyRunner module, you need add ddmlib.jar,guavalib.jar,sdklib.jar,chimpchat.jar under {your android sdk}/tools/lib path into your java build path.

# Example
   - WebViewAppItclTestCase : The ios app is WebViewApp by Appium example
   - MteMonkeyRunnerTest : MteMonkeyRunner example like MonkeyRunner(Android)

# Configuration
   - MteMonkeyRunner module : com.mte.android.mmr
   - Other packages belong to Selenium and Appium framework

# Declaration
In this project, some source codes come from internet and other test automation project, the purpose is only for sharing and communication with everyone who like test automation. If you don't want to integrate your code, please tell me to remove them. Thanks
