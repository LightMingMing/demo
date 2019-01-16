### Hello, Git

```
git add readme.md

git commit -m message

git push origin master -u

// input username and password
```

```
git --help

start a working area (see also: git help tutorial)
   clone      Clone a repository into a new directory
   init       Create an empty Git repository or reinitialize an existing one

work on the current change (see also: git help everyday)
   add        Add file contents to the index
   mv         Move or rename a file, a directory, or a symlink
   reset      Reset current HEAD to the specified state
   rm         Remove files from the working tree and from the index

examine the history and state (see also: git help revisions)
   bisect     Use binary search to find the commit that introduced a bug
   grep       Print lines matching a pattern
   log        Show commit logs
   show       Show various types of objects
   status     Show the working tree status

grow, mark and tweak your common history
   branch     List, create, or delete branches
   checkout   Switch branches or restore working tree files
   commit     Record changes to the repository
   diff       Show changes between commits, commit and working tree, etc
   merge      Join two or more development histories together
   rebase     Reapply commits on top of another base tip
   tag        Create, list, delete or verify a tag object signed with GPG

collaborate (see also: git help workflows)
   fetch      Download objects and refs from another repository
   pull       Fetch from and integrate with another repository or a local branch
   push       Update remote refs along with associated objects
```

### Mockito

#### Basic Step

```java
1. mock/spy
2. when
3. thenReturn thenThrow thenAnswer
4. verify

1. mock/spy
2. doReturn doThrow doAnswer
3. when
4. verify
```

#### ArgumentMatchers

```java
<T> T any();
<T> T any(Class<T> type);
<T> T isA(Class<T> type);
int anyInt(); // byte、short、double、float、long...
String anyString();

<T> List<T> anyList();
<T> List<T> anyListOf(Class<T>); //Set、Map、Collection

boolean eq(byte); // byte、boolean、short、int...

<T> T isNull();
<T> T isNull(Class<T>);
<T> T notNull();
<T> T notNull(Class<T>);
<T> T isNotNull();
<T> T isNotNull(Class<T>);
<T> T nullable(Class<T>);

String matches(String);
String matches(Pattern);
String endsWith(String);
String startWith(String);

// customize argument matcher
<T> T argThat(ArgumentMatcher<T>);
char charThat(ArgumentMather<Character>); // byte、boolean...
```

```java
public interface ArgumentMatcher<T> {
    boolean matches(T argument);
}
```

#### AdditionalAnswers

```java
<T> Answer<T> returnsFirstArg();
<T> Answer<T> returnsSecondArg();
<T> Answer<T> returnsLastArg();
<T> Answer<T> returnsArgAt(int);

<T> Answer<T> delegatesTo(Object);

<T> Answer<T> returnsElementsOf(Collection<?>);

<T> Answer<T> answersWithDelay(long, Answer<T>);

<T> Answer<T> answer(Answer1<T, A>);
Ansower<Void> answerVoid(VoidAnswer1<A>);
```

#### VerificationMode

```java
public class Mockito extends ArgumentMatchers {
    static VerificationMode times(int);
    static VerificationMode never();
    static VerificationMode atLeastOnce();
    static VerificationMode atLeast(int);
    static VerificationMode atMost(int);
    static VerificationMode calls(int);
    static VerificationMode only();
    static VerificationMode timeout(long);
    static VerificationMode after(long);
}
```

#### Little demo

```java
//1.
mock.foo();
verify(mock, after(1000)).foo();
//waits 1000 millis and succeeds

//2.
mock.foo();
verify(mock, timeout(1000));
//succeeds immediately
```

```java
Class<UserRepository> clazz = UserRepository.class;
UserRepository userRepository = Mockito.mock(clazz);
Mockito.when(userRepository.count()).thenReturn(123);
Mockito.when(userRepository.getName("MingMing")).thenReturn("ZhaoMingMing");

userRepository.count();
userRepository.getName("MingMing");
userRepository.getName("MingMing");
userRepository.getName("MingMing");

Mockito.verify(userRepository).count();
// Equals Mockito.verify(userRepository, Mockito.time(1)).count();

Mockito.verify(userRepository, Mockito.atLeast(1)).getName("MingMing");
Mockito.verify(userRepository, Mockito.atLeast(2)).getName("MingMing");
```

```java
Echo echo = Mockito.mock(Echo.class);
Mockito.doAnswer(AdditionalAnswers.returnsFirstArg()).when(echo).echo(Mockito.any());
Assert.assertEquals(echo.echo("firstArg", "secondArg", "thirdArg"), "firstArg");
```

