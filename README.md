# EilangenApp - This application is for EIMETALS... We have different operations defined to add or remove eggs..

Following operations are supported:
Insert, pop, show, exit, index, get, remove, insertFront, popFront, sort, isSorted, push, EOF...

Every operation followed by arguments related to the command. Operation can allow 1 or more modifications.

Supported Modifications:
@once
@twice
@often
@dryrun
@sorted

Exceptions:
1. When no command / bad command is entered.
2. When arguments does not match with command.
3. When invalid modifieres are entered.

To run the application  in the local
$ git clone https://github.com/UmaSubbaiyan/EilangenApp.git 
$ go to the folder where it is done
$ mvn package
$ java -cp target/Eilangen-0.0.1-SNAPSHOT.jar com.eimetals.eilangen.EilangenApplication

Sample commands:
show
[]
insert 1
insert 1 @dryrun
[1 1]
insertFront 3 2
show
[3 2 1]
isSorted
false
sort
pop @twice
show
[1]
exit
