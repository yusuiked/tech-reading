const MY_DATA = 'hoge';
MY_DATA = 'foobar';

/*
repl: "MY_DATA" is read-only
  1 | const MY_DATA = 'hoge';
> 2 | MY_DATA = 'foobar';
    | ^
*/
