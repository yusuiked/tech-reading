const MY_ARRAY = [10, 20, 30];
MY_ARRAY[0] = 50;
MY_ARRAY = [100, 200, 300];

/*
repl: "MY_ARRAY" is read-only
  1 | const MY_ARRAY = [10, 20, 30];
  2 | MY_ARRAY[0] = 50;
> 3 | MY_ARRAY = [100, 200, 300];
    | ^
*/
