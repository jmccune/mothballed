 console.log("LOADED JASMINE EXEC SCRIPT");
 (function() {
      console.log("RUNNING JASMINE STARTUP/EXEC");
      var jasmineEnv = jasmine.getEnv();
      jasmineEnv.updateInterval = 1000;

      var htmlReporter = new jasmine.HtmlReporter();

      jasmineEnv.addReporter(htmlReporter);

      jasmineEnv.specFilter = function(spec) {
        return htmlReporter.specFilter(spec);
      };

      var currentWindowOnload = window.onload;

      window.onload = function() {
        if (currentWindowOnload) {
          currentWindowOnload();
        }
        console.log("EXECUTING JASMINE!!!!>>>!>!>!>!>");
        execJasmine();
      };

      function execJasmine() {
        jasmineEnv.execute();
      }

    })();