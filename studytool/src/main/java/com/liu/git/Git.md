### 将dev分支合并到feature/sdk1.0-test

```shell
切换分支 git checkout dev
查看日志 git log
回滚到指定hard git reset --hard 71e4b64d7cc185e95a289c8dd795a0cf80977da8
当前分支合并到“feature/sdk1.0-test” git merge feature/sdk1.0-test
添加全部文件文件到暂存区 git add -A
提交暂存区文件到仓库区 git commit -m "添加策略"
提交代码 git push

git checkout dev           #切换到dev开发分支
git pull
git checkout master
git dev              #合并dev分支到master上
git push origin master     #将代码推到master上

# 生成一个可供发布的压缩包
git archive
```

### 常用命令

```shell
# 列出所有本地分支
git branch

# 列出所有远程分支
git branch -r

# 切换到dev分支
git checkout dev

# 显示当前分支的版本历史
git log

# 显示commit历史，以及每次commit发生变更的文件
git log --stat

# 重置当前分支的HEAD为指定commit，同时重置暂存区和工作区，与指定commit一致
git reset --hard [commit]

# 合并指定分支到当前分支
git merge [branch]

# 添加当前目录的所有文件到暂存区
git add .

# 提交暂存区到仓库区
git commit -m [message]

# 上传本地指定分支到远程仓库
git push [remote] [branch]
```

