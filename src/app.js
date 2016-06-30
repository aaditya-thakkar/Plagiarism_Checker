var arg = "/home/aaditya/Desktop/new2.txt";

// compile ParserException.java
var compile = require('child_process').spawn(
  'javac', ['tokenizer/*.java']
);
compile.stdout.on('data', function(data) {
    console.log(data.toString());
});
compile.stderr.on("data", function (data) {
    console.log(data.toString());
});

setTimeout(function(){
  var makejar = require('child_process').spawn(
    'jar', ['cmf', 'MyJar.jar', 'Manifest.txt', 'tokenizer/*.class']  //jar cfe myJar.jar myClass myClass.class
  );
  makejar.stdout.on('data', function(data) {
      console.log(data.toString());
  });
  makejar.stderr.on("data", function (data) {
      console.log(data.toString());
  });
},8000);

setTimeout(function(){
  var child = require('child_process').spawn(
    'java', ['-jar', 'MyJar.jar', 'arg']
  );
  child.stdout.on('data', function(data) {
      console.log(data.toString());
  });
  child.stderr.on("data", function (data) {
      console.log(data.toString());
  });

},10000);
