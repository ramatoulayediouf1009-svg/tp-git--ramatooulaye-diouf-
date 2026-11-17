# Réponses aux questions du TP Git & GitHub 

## Partie 1 : Installer et configurer Git

### Q1. Dans quel fichier l'option `--global` enregistre-t-elle la configuration ? Quelle différence avec une configuration sans `--global`, faite dans un dépôt (fichier `.git/config`) ?
- L'option `--global` enregistre la configuration dans le fichier de configuration global de l'utilisateur situé dans son dossier personnel (généralement `~/.gitconfig` sous Linux/macOS). 
- Une configuration sans `--global`, effectuée à l'intérieur d'un dépôt, est stockée localement dans le fichier `.git/config` de ce dépôt spécifique. La configuration locale prime sur la configuration globale pour ce dépôt.

### Q2. Pourquoi l'email configuré doit-il correspondre à celui du compte GitHub ?
- L'email doit correspondre pour que GitHub puisse lier les commits envoyés (*pushes*) à votre profil utilisateur. Cela permet d'associer correctement vos contributions à votre historique de contributions et à votre identité visuelle sur la plateforme.

---

## Partie 2 : Premier dépôt local

### Q3. Que contient le dossier `.git` ? Que perdrait-on en le supprimant ?
- Le dossier `.git` contient toute la métadonnée et la base de données de contrôle de version de Git : l'historique des commits, les branches, les références, les objets compressés et la zone de transit (*staging area* / index).
- En le supprimant, on perd tout l'historique du projet, les branches et l'état versionné. Le dossier redevient un simple répertoire de fichiers sans suivi Git.

### Q4. Quelle différence entre `git diff` et `git diff --staged` ?
- `git diff` montre les modifications apportées dans le répertoire de travail qui n'ont **pas encore** été indexées (non ajoutées par `git add`).
- `git diff --staged` (ou `--cached`) montre les modifications qui ont été **indexées** (ajoutées avec `git add`) et qui sont prêtes à partir dans le prochain commit.

### Q5. Que représente la suite de caractères au début de chaque ligne de `git log --oneline` ?
- C'est l'identifiant unique (**hash SHA-1** abrégé, généralement sur 7 caractères) du commit, qui permet de le référencer précisément (pour un affichage, un retour en arrière, un reset, etc.).

---

## Partie 3 : Corriger et annuler

### Q6. Pourquoi ne versionne-t-on pas le fichier compilé `Main.class` ? En quoi `.gitignore` simplifie-t-il l'usage de `git add` ?
- On ne versionne pas les fichiers compilés (`.class`) car ils sont générés automatiquement à partir du code source (`.java`). Les versionner alourdirait inutilement le dépôt avec des fichiers dérivés et source de conflits potentiels.
- Le fichier `.gitignore` indique à Git quels fichiers ignorer automatiquement, ce qui évite qu'ils n'apparaissent comme non suivis (*untracked*) et permet d'utiliser des commandes globales comme `git add .` sans risque d'ajouter des fichiers superflus.

### Q7. Quelle différence entre `git revert` et `git reset` ? Lequel utiliser sur une branche déjà partagée sur GitHub, et pourquoi ?
- `git revert` annule les modifications d'un commit en créant un **nouveau commit inverse**, préservant ainsi l'historique existant. `git reset` déplace le pointeur de la branche en arrière, ce qui modifie ou supprime l'historique local.
- Sur une branche partagée sur GitHub, il faut privilégier **`git revert`** pour ne pas perturber l'historique des autres collaborateurs qui possèdent déjà les commits distants.

### Q8. Que signifie `HEAD~1` ? Que veut dire le message « detached HEAD » vu à l'étape 3.8 ?
- `HEAD~1` désigne l'état situé un cran avant le commit actuel (le parent direct du commit pointé par `HEAD`).
- Le mode « **detached HEAD** » (HEAD détaché) signifie que le pointeur `HEAD` est rattaché directement à un commit précis (via son hash) plutôt qu'à une branche. Les commits faits dans cet état ne sont rattachés à aucune branche active.

---

## Partie 4 : Branches, fusion et conflits

### Q9. Pourquoi la multiplication est-elle absente de `Main.java` à l'étape 4.2 alors que le fichier est le même ?
- Parce que la modification a été réalisée et enregistrée sur une autre branche (`multiplication`), qui constitue un espace de travail parallèle indépendant de la branche `main` sur laquelle vous vous trouvez.

### Q10. Qu'est-ce qu'une fusion fast-forward ? Pourquoi la fusion de l'étape 4.4 a-t-elle créé un commit de fusion ?
- Une fusion *fast-forward* se produit lorsque la branche principale n'a subi aucune modification depuis la création de la branche dérivée : Git se contente de déplacer le pointeur de la branche principale vers le dernier commit de la branche secondaire.
- La fusion de l'étape 4.4 a créé un commit de fusion car les deux branches (`main` et `titre`) avaient divergé (des modifications concurrentes avaient été apportées sur chacune d'elles).

### Q11. Que signifient `<<<<<<< HEAD`, `=======` et `>>>>>>> titre` ? Dans quel cas Git ne peut-il pas fusionner seul ?
- Ce sont des **marqueurs de conflit** ajoutés par Git :
  - `<<<<<<< HEAD` : indique le début de vos modifications sur la branche courante.
  - `=======` : sert de séparateur.
  - `>>>>>>> titre` : indique la fin des modifications provenant de la branche fusionnée (`titre`).
- Git ne peut pas fusionner seul lorsque des modifications contradictoires ont été apportées sur les mêmes lignes de code dans les deux branches.

---

## Partie 5 : Publier sur GitHub

### Q12. Que désigne `origin` ? Que fait l'option `-u` de `git push` ?
- `origin` est l'alias par défaut donné au dépôt distant hébergé sur GitHub lors de son association avec le dépôt local.
- L'option `-u` (ou `--set-upstream`) associe la branche locale courante à la branche distante correspondante. Cela permet de simplifier les futurs envois et récupérations en tapant simplement `git push` ou `git pull` sans repréciser le nom du dépôt et de la branche.

### Q13. Quelle différence entre `git fetch` et `git pull` ?
- `git fetch` télécharge les nouveautés du dépôt distant sans les intégrer dans votre espace de travail actuel.
- `git pull` combine `git fetch` et une action de fusion (`git merge`) pour télécharger et intégrer directement les modifications distantes dans votre branche courante.

### Q14. Pourquoi le `git push` de l'étape 5.5 a-t-il été refusé ? Quelle est la bonne habitude à prendre avant de pousser ?
- Le push a été refusé car le dépôt distant comportait des commits que vous n'aviez pas en local (divergence d'historique).
- La bonne habitude à prendre est d'exécuter un `git pull` (ou `git fetch` suivi d'un `git merge`) pour intégrer les dernières modifications distantes avant d'envoyer votre propre travail.

---

## Partie 6 : Collaborer : fork, pull request et issues

### Q15. Quelle différence entre un fork, un clone et une branche ?
- Un **fork** est une copie indépendante d'un dépôt hébergé sur le serveur distant (sur le compte d'un autre utilisateur).
- Un **clone** est la copie locale d'un dépôt distant sur votre machine.
- Une **branche** est une ligne de développement interne à un dépôt (qu'il soit local ou distant).

### Q16. Pourquoi propose-t-on une PR depuis une branche dédiée plutôt que depuis `main` ?
- Travailler sur une branche dédiée permet d'isoler la fonctionnalité ou la correction développée, de faciliter la relecture de code, de garder la branche `main` propre et de pouvoir soumettre plusieurs contributions distinctes en parallèle.

### Q17. À quoi servent les remotes `origin` et `upstream` dans le dépôt de B ?
- `origin` pointe vers le **fork personnel** de l'utilisateur B (où il peut pousser ses modifications librement).
- `upstream` pointe vers le **dépôt d'origine** (celui de A), ce qui permet à B de récupérer les mises à jour de référence de ce dépôt principal.

---

## Partie 7 : Entraînement interactif LearnGitBranching

### Q18. Niveaux réalisés sur LearnGitBranching
- **Niveau 1 :** Introduction aux commits (`introduction`)
  - Commandes : `git commit`, `git commit`
  - Score : Optimal (égal à la solution de référence).
- **Niveau 2 :** Les branches dans Git (`git branch`)
  - Commandes : `git branch bugFix`, `git checkout bugFix`
  - Score : Optimal.
- **Niveau 3 :** Fusionner des branches (`git merge`)
  - Commandes : `git checkout -b bugFix`, `git commit`, `git checkout main`, `git commit`, `git merge bugFix`
  - Score : Optimal.