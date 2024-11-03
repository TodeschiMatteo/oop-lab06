# Esercizio di risoluzione di un merge conflict

**Il tempo massimo in laboratorio per questo esercizio è di _20 minuti_.
Se superato, sospendere l'esercizio e riprenderlo per ultimo!**

Si visiti https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test.
Questo repository contiene due branch: `master` e `feature`

Per ognuna delle seguenti istruzioni, si annoti l'output ottenuto.
Prima di eseguire ogni operazione sul worktree o sul repository,
si verifichi lo stato del repository con `git status`.

1. Si cloni localmente il repository
``` shell
$ git clone https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test exercise
Cloning into 'exercise'...
remote: Enumerating objects: 12, done.
remote: Counting objects: 100% (4/4), done.
remote: Compressing objects: 100% (3/3), done.
remote: Total 12 (delta 1), reused 1 (delta 1), pack-reused 8 (from 1)
Receiving objects: 100% (12/12), done.
Resolving deltas: 100% (2/2), done.
$ cd exercise/
$ git status
On branch master
Your branch is up to date with 'origin/master'.

nothing to commit, working tree clean
```

2. Ci si assicuri di avere localmente entrambi i branch remoti
git checkout -b feature origin/feature
``` shell
$ git branch -v
* master 8e0f29c Change HelloWorld to print the number of available processor
$ git checkout -b feature origin/feature
Switched to a new branch 'feature'
branch 'feature' set up to track 'origin/feature'.
$ git branch -v
* feature bed943f Print author information
  master  8e0f29c Change HelloWorld to print the number of available processors
$ git status
On branch feature
Your branch is up to date with 'origin/feature'.

nothing to commit, working tree clean
```

3. Si faccia il merge di `feature` dentro `master`, ossia: si posizioni la `HEAD` su `master`
   e da qui si esegua il merge di `feature`
``` shell
$ git checkout master
Switched to branch 'master'
Your branch is up to date with 'origin/master'.
$ git status
On branch master
Your branch is up to date with 'origin/master'.

nothing to commit, working tree clean
$ git merge feature
Auto-merging HelloWorld.java
CONFLICT (content): Merge conflict in HelloWorld.java
Automatic merge failed; fix conflicts and then commit the result.
```
   
4. Si noti che viene generato un **merge conflict**!
`Auto-merging HelloWorld.java CONFLICT (content): Merge conflict in HelloWorld.java Automatic merge failed; fix conflicts and then commit the result.`

5. Si risolva il merge conflict come segue:
   - Il programma Java risultante deve stampare sia il numero di processori disponibili
     (funzionalità presente su `master`)
     che il nome dell'autore del file
     (funzionalità presente su `feature`)
``` s
public final class HelloWorld {

        private static final String AUTHOR = "Danilo Pianini";

        public static void main(final String[] args) {
                System.out.println("This program has been realised by " + AUTHOR);
                System.out.println("This program is running in a PC with " + procNumber() + " logic processors!");
        }

        public static int procNumber() {
                return Runtime.getRuntime().availableProcessors();
        }
}
```
``` shell
$ javac HelloWorld.java
$ java HelloWorld
This program has been realised by Danilo Pianini
This program is running in a PC with 8 logic processors!
$ git add HelloWorld.java
$ git commit "merged"
fatal: cannot do a partial commit during a merge.
$ git commit --no-edit
[master 0983a5d] Merge branch 'feature'
$ git log --all --graph
*   commit 0983a5d51c3b1a49d9de5d777ac91b02a3a2b54b (HEAD -> master)
|\  Merge: 8e0f29c bed943f
| | Author: TodeschiMatteo <matteo.todeschi2@studio.unibo.it>
| | Date:   Sun Nov 3 16:25:36 2024 +0100
| |
| |     Merge branch 'feature'
| |
| |     # Conflicts:
| |     #       HelloWorld.java
| |
| * commit bed943fbdd6ba94e64197448e4754a529d984e88 (origin/feature, feature)
| | Author: Danilo Pianini <danilo.pianini@gmail.com>
| | Date:   Thu Oct 27 17:21:22 2016 +0200
| |
| |     Print author information
| |
* | commit 8e0f29c12e060f3bdc62540343eff3e473616d61 (origin/master, origin/HEAD)
|/  Author: Danilo Pianini <danilo.pianini@gmail.com>
|   Date:   Thu Oct 27 17:19:05 2016 +0200
|
|       Change HelloWorld to print the number of available processors
|
* commit d956df66aeb0829f23b7b3d0d9a1c002c390f87f    
| Author: Danilo Pianini <danilo.pianini@gmail.com>  
| Date:   Thu Oct 27 17:17:43 2016 +0200
|
|     Create .gitignore
|
* commit 700ee0b669f6cd75384abb9af51ca5c2adefe917    
  Author: Danilo Pianini <danilo.pianini@gmail.com>  
  Date:   Thu Oct 27 17:15:10 2016 +0200

      Create HelloWorld
(END)
On branch master
Your branch is ahead of 'origin/master' by 2 commits.
  (use "git push" to publish your local commits)     

nothing to commit, working tree clean
```

6. Si crei un nuovo repository nel proprio github personale

7. Si aggiunga il nuovo repository creato come **remote** e si elenchino i remote
``` shell
$ git remote add mygit https://github.com/TodeschiMatteo/61-git-remotes-merge-conflict.git
$ git remote -v
mygit   https://github.com/TodeschiMatteo/61-git-remotes-merge-conflict.git (fetch)
mygit   https://github.com/TodeschiMatteo/61-git-remotes-merge-conflict.git (push)
origin  https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test (fetch)
origin  https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test (push)
```

8. Si faccia push del branch `master` sul proprio repository
``` shell
$ git push mygit master
Enumerating objects: 15, done.
Counting objects: 100% (15/15), done.
Delta compression using up to 8 threads
Compressing objects: 100% (11/11), done.
Writing objects: 100% (15/15), 1.60 KiB | 819.00 KiB/s, done.
Total 15 (delta 4), reused 10 (delta 2), pack-reused 0
remote: Resolving deltas: 100% (4/4), done.
To https://github.com/TodeschiMatteo/61-git-remotes-merge-conflict.git
 * [new branch]      master -> master
```

9. Si setti il branch remoto `master` del nuovo repository come *upstream* per il proprio branch `master` locale
``` shell
$ git branch --set-upstream-to=mygit/master
branch 'master' set up to track 'mygit/master'.
```
