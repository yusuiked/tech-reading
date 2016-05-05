const OBJ = { x:10 };
OBJ.x = 100;
OBJ = { y:200 };

/*
repl: "OBJ" is read-only
  1 | const OBJ = { x:10 };
  2 | OBJ.x = 100;
> 3 | OBJ = { y:200 };
    | ^
*/
