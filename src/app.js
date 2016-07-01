
var path = require('path');
var appDir = path.dirname(require.main.filename);
var arg = appDir+"/new.txt"  //change name of your test 1 files in src folder
var arg1 = appDir+"/new2.txt"  //change name of your test 2 files in src folder
console.log("path is "+arg1);
// compile java files to make .class files
var compile = require('child_process').spawn(
  'javac', ['tokenizer/Tuple.java', 'tokenizer/ParserException.java', 'tokenizer/Tokenizer.java', 'tokenizer/Test.java']
);
compile.stdout.on('data', function(data) {
    console.log(data.toString());
});
compile.stderr.on("data", function (data) {
    console.log(data.toString());
});

// make a jar with an entry point specified in Manifest.txt
setTimeout(function(){
  var makejar = require('child_process').spawn(
    'jar', ['cfm', 'MyJar.jar', 'META-INF/MANIFEST.MF', 'tokenizer/Tuple.class', 'tokenizer/ParserException.class', 'tokenizer/Tokenizer.class', 'tokenizer/Tuple.class', 'tokenizer/Tokenizer$Token.class', 'tokenizer/Tokenizer$TokenInfo.class', 'tokenizer/Test.class' ]
  );
  makejar.stdout.on('data', function(data) {
      console.log(data.toString());
  });
  makejar.stderr.on("data", function (data) {
      console.log(data.toString());
  });
},1000);

// executing a jar file
setTimeout(function(){
  var child = require('child_process').spawn(
    'java', ['-jar', 'MyJar.jar', arg, arg1]
  );
  child.stdout.on('data', function(data) {
      console.log(data.toString());
  });
  child.stderr.on("data", function (data) {
      console.log(data.toString());
  });
},2000);
