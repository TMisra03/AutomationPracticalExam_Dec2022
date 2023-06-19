$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/TechFiosWebpageColor.feature");
formatter.feature({
  "name": "Techfios billing login page functionality validation",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@webpageColorFeature"
    }
  ]
});
formatter.scenario({
  "name": "Sky Blue Background",
  "description": "Given: \"Set SkyBlue Background\" button exists \nWhen: I click on the Set SkyBlue Background button \nThen: The background color will change to sky blue",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@webpageColorFeature"
    },
    {
      "name": "@colorScenario1"
    }
  ]
});
});