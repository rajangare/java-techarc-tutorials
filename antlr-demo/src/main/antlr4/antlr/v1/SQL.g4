grammar SQL;

sql_stmt: select_stmt;

select_stmt: SELECT select_list FROM table_list (WHERE expr)?;

select_list: '*'
          | column_list;

column_list: column_name (',' column_name)*;

table_list: table_name (',' table_name)*;

expr: column_name '=' value;

column_name: ID;

table_name: ID;

value: INT | STRING;

SELECT: 'SELECT';
FROM: 'FROM';
WHERE: 'WHERE';
ID: [a-zA-Z_][a-zA-Z_0-9]*;
INT: [0-9]+;
STRING: '\'' .*? '\'';
WS: [ \t\r\n]+ -> skip;
