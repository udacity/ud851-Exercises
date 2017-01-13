#! /usr/local/bin/python

import argparse
import os
import shutil
import sys
import tempfile

import git

IGNORE_PATTERNS = ('.git', ".DS_Store")
SAFE_CHARS = ["-", "_", "."]
MAX_LENGTH = 100

STUDENT = "student"
DEVELOP = "develop-"
DEVELOP_DEFAULT = "all develop branches"




def flatten(repo_dir, target_dir, student, develop_branches, remove_branches, links):
    repo = git.Repo(repo_dir)

    if develop_branches == DEVELOP_DEFAULT:
        develop_branches = [branch for branch in repo.branches if DEVELOP in branch.name]

    remove_local_branches(repo, student, develop_branches)

    try:
        temp_dir = tempfile.mkdtemp()


        for develop in develop_branches:
            to_temp_dir(repo, repo_dir, develop, temp_dir)

        copy_snapshots(repo, student, temp_dir, target_dir)
    finally:
        if os.path.exists(temp_dir):
            shutil.rmtree(temp_dir)

    print "Done! Review and commit the", student, "branch at your leisure."
    print "Then run $ git push --all --prune"


def remove_local_branches(repo, student, develop_branches):
    for branch in repo.branches:
        if branch.name != student and branch not in develop_branches:
            print "Removing local branch:", branch.name
            repo.git.branch(branch.name, "-D")


def to_temp_dir(repo, repo_dir, develop, temp_dir):
    for rev in repo.git.rev_list(develop).split("\n"):
        commit = repo.commit(rev)
        branch_name = clean_commit_message(commit.message)
        if "Exercise" in branch_name or "Solution" in branch_name:
            if branch_name in repo.branches:
                repo.git.branch(branch_name, "-D")
            new_branch = repo.create_head(branch_name)
            new_branch.set_commit(rev)

            repo.git.checkout(commit)
            print "Saving snapshot of:", branch_name
            repo.git.clean("-fdx")
            folder_name = develop.name.split("-",1)[1]
            target_dir = os.path.join(temp_dir, folder_name, branch_name)

            shutil.copytree(repo_dir, target_dir,
                            ignore=shutil.ignore_patterns(*IGNORE_PATTERNS))


def clean_commit_message(message):
    first_line = message.split("\n")[0]
    safe_message = "".join(
        c for c in first_line if c.isalnum() or c in SAFE_CHARS).strip()
    return safe_message[:MAX_LENGTH] if len(safe_message) > MAX_LENGTH else safe_message


def copy_snapshots(repo, student, temp_dir, target_dir):
    if target_dir == os.getcwd():
        repo.git.checkout(student)
    for item in os.listdir(temp_dir):
        source_dir = os.path.join(temp_dir, item)
        dest_dir = os.path.join(target_dir, item)

        if os.path.exists(dest_dir):
            shutil.rmtree(dest_dir)
        print "Copying: ", item
        shutil.copytree(source_dir, dest_dir)


DESCRIPTION = "This script "

EPILOG = " To make changes to "


def main():
    parser = argparse.ArgumentParser(
        description=DESCRIPTION,
        epilog=EPILOG,
        formatter_class=argparse.ArgumentDefaultsHelpFormatter)

    parser.add_argument('-b', '--remove',
                        action='store_true',
                        help='delete all local branches except the student and develop branches')

    parser.add_argument('-d', '--directory',
                        default=os.getcwd(),
                        help="the directory of the source repository")

    parser.add_argument('-t', '--target',
                        default=os.getcwd(),
                        help="target directory")

    parser.add_argument('-s', '--student',
                        default=STUDENT,
                        help="branch where snapshots will be copied")

    parser.add_argument('-l', '--links',
                        action='store_true',
                        help="Add links to branches and diff to README files")

    parser.add_argument('develop_branches',
                        nargs="*",
                        default=DEVELOP_DEFAULT,
                        help="the branches where snapshots will be copied from")

    parsed = parser.parse_args()

    flatten(
        parsed.directory,
        parsed.target,
        parsed.student,
        parsed.develop_branches,
        parsed.remove,
        parsed.links
    )


if __name__ == "__main__":
    sys.exit(main())
