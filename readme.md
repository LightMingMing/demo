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

[LearnGit](https://learngitbranching.js.org/?NODEMO)

```bash
git branch bugFix
git checkout bugFix

git checkout -b bugFix

git merge bugFix
git rebase master
git rebase master bugFix
git rebase bugFix side
git rebase side another

HEAD 指向当前分支最近一次提交记录，一般都是分支名
分离的HEAD，指向具体提交记录，而不是分支名

相对引用 ^向上移动一个提交记录 ~<num>向上移动多个提交记录

git checkout HEAD^
git checkout master^^
git checkout HEAD^2 2个父节点

git branch -f master HEAD~3 分支指向另一个提交

git reset HEAD~1 撤销本地改动(回退分支记录)
git revert HEAD 撤销远程分支改动

git cherry-pick <提交号...> 将提交记录放到当前分支

git rebase -i 交互式rebase,列出将要被复制到目标分支的备选提交记录
git rebase -i HEAD~4 调整顺序或删除提交记录


git tag tagName HEAD 创建标签
git tag v1 HEAD

git describe <ref>
	<tag>_<numCommits>_g<hash>

git branch bugWork master^^2^
```

```bash
git clone 

origin/master 远程分支 <remote_name>/<branch_name>
在它上提交会进入分离HEAD状态，远程分支只有在远程仓库中相应的分支更新了才会更新

git fetch 远程仓库的更新下载至远程分支，更新远程分支指针，不会改变本地仓库的状态

当远程分支中有新的提交时，可以向合并本地分支那样合并远程分支
git cherry-pick origin/master
git rebase origin/master
git merge origin/master

git fetch
git merge origin/master  
== git pull

git fetch
git rebase origin/master
== git pull --rebase

git push 向远程远程仓库提交记录、远程分支也会更新
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

Echo echo = Mockito.mock(Echo.class);
Mockito.when(echo.echo(Mockito.any())).thenAnswer(i -> {
	return i.getArgument(0);
});
Assert.assertEquals(echo.echo("firstArg", "secondArg", "thirdArg"), "firstArg");
```

### byte-buddy(伙伴)

[官方帮助文档](http://bytebuddy.net/#/tutorial)

> Byte Buddy is a code generation and manipulation library for creating and modifying Java classes during the runtime of a Java application and without the help of a compiler.  
>
> Java字节码生成和修改库，能够在运行期间，创建或修改Java类

```java
Class<?> dynamicType = new ByteBuddy().subclass(Object.class).method(ElementMatchers.named("toString"))
				.intercept(FixedValue.value("Hello, world!")).make().load(SimpleDemo.class.getClassLoader())
				.getLoaded();
System.out.println(dynamicType.getName());
System.out.println(dynamicType.newInstance().toString());

/* output:
net.bytebuddy.renamed.java.lang.Object$ByteBuddy$csGs4nHo
Hello, world!
 */
```

```java
DynamicType.Unloaded<Object> uploaded = new ByteBuddy(ClassFileVersion.JAVA_V8).subclass(Object.class).make();
DynamicType.Loaded<Object> loaded = uploaded.load(SimpleDemo.class.getClassLoader());
		Files.write(Paths.get("D:/", "DynamicType.class"), loaded.getBytes(), StandardOpenOption.CREATE);
```

```java
D:\>javap -v DynamicType
警告: 二进制文件DynamicType包含net.bytebuddy.renamed.java.lang.Object$ByteBuddy$WhaIw1Rt
Classfile /D:/DynamicType.class
  Last modified 2019-1-21; size 172 bytes
  MD5 checksum 68f39d3c8969aaa14d5755fb57a0fbfa
public class net.bytebuddy.renamed.java.lang.Object$ByteBuddy$WhaIw1Rt
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Utf8               net/bytebuddy/renamed/java/lang/Object$ByteBuddy$WhaIw1Rt
   #2 = Class              #1             // net/bytebuddy/renamed/java/lang/Object$ByteBuddy$WhaIw1Rt
   #3 = Utf8               java/lang/Object
   #4 = Class              #3             // java/lang/Object
   #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = NameAndType        #5:#6          // "<init>":()V
   #8 = Methodref          #4.#7          // java/lang/Object."<init>":()V
   #9 = Utf8               Code
{
  public net.bytebuddy.renamed.java.lang.Object$ByteBuddy$WhaIw1Rt();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #8                  // Method java/lang/Object."<init>":()V
         4: return
}
```

