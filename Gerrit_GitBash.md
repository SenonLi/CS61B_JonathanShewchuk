### Set Up SSH Keys in Gerrit
#### Add SSH Key to use with Git
* $ eval 'ssh-agent'
* $ exec ssh-agent bash
* $ ssh-add ~/.ssh/id_rsa  (Windows: $ ssh-add ./id_rsa)
* add SSH Key to both of Git Settings "SSH and GPG keys" and Gerrit Settings
	https://gerrit.wikimedia.org/r/#/settings/ssh-keys
	"SSH Public Keys"

### How to Submit a Patch
#### Push your change set to Gerrit
(git review -s Problems encountered installing commit-msg hook)
* curl https://gerrit.wikimedia.org/r/tools/hooks/commit-msg > .git/hooks/commit-msg
* chmod u+x .git/hooks/commit-msg
