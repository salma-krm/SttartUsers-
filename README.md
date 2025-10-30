# QUESTIONS POUR CRÉER UNE DOCUMENTATION - SPRING CORE / SPRING DATA / SPRING MVC

## SPRING CORE - Inversion de Contrôle & Injection de Dépendances
### 1. Qu'est-ce que Spring Core et à quoi sert-il dans une application Java ?

Spring Core est le module fondamental du Spring Framework. Il fournit le conteneur IoC (Inversion of Control) qui gère le cycle de vie des objets (beans) et leurs dépendances.
Rôles principaux :

- Gestion automatique des objets (création, configuration, destruction).
- Injection de dépendances entre les composants.
- Configuration centralisée de l'application.
- Découplage entre les classes.

### 2. Que signifie le principe d'Inversion de Contrôle (IoC) ?

IoC signifie que le framework contrôle le flux d'exécution et la gestion des objets, et non le développeur. Avant IoC, le développeur créait et gérait manuellement les objets. Avec IoC, Spring prend en charge cette responsabilité automatiquement.

### 3. Quelle est la différence entre IoC et Injection de Dépendances (DI) ?

#### IoC (Inversion of Control) :
Principe général où le framework contrôle la création des objets. C'est une philosophie architecturale.
#### DI (Dependency Injection) :
Implémentation concrète de l'IoC où le framework injecte les dépendances nécessaires. C'est une technique pratique.

IoC est le concept, DI est la mise en pratique.

### 4. Qu'est-ce qu'un bean dans Spring ?
Un bean est un objet géré par le conteneur Spring.
Caractéristiques :

- Créé et configuré par Spring
- Son cycle de vie est géré par Spring
- Peut être injecté dans d'autres beans
- Singleton par défaut (une seule instance)

Les beans peuvent être déclarés via des annotations stéréotypées (@Component, @Service, @Repository, @Controller) ou via @Bean dans une classe @Configuration.

### 5. Quel est le rôle du conteneur IoC ?
Le conteneur IoC (ApplicationContext) est le cœur de Spring. Il :

- Scanne les packages pour détecter les beans
- Crée les instances des beans
- Injecte les dépendances entre les beans
- Gère le cycle de vie (initialisation, destruction)
- Fournit les beans aux classes qui en ont besoin

### 6. Quelle est la différence entre ApplicationContext et BeanFactory ?
#### BeanFactory :
Interface de base qui fournit les fonctionnalités minimales de gestion des beans. Chargement lazy (à la demande).

#### ApplicationContext :
Interface plus complète qui hérite de BeanFactory et ajoute :

- Internationalisation (i18n)
- Gestion d'événements
- Intégration AOP
- Chargement automatique au démarrage (eager loading)

ApplicationContext est recommandé pour la plupart des applications. BeanFactory n'est utilisé que dans des cas très spécifiques où les ressources sont limitées.

### 7. Quelles sont les trois approches de configuration dans Spring (XML, annotations, Java Config) ?
1. Configuration XML (Legacy) : Déclaration des beans et dépendances dans des fichiers XML. Verbeux et sans vérification à la compilation.
2. Configuration par Annotations : Utilisation d'annotations comme @Component, @Service, @Autowired directement dans le code Java. Plus concis et avec vérification à la compilation.
3. Configuration Java (Recommandée) : Utilisation de classes annotées @Configuration avec des méthodes @Bean. Type-safe, facile à refactorer, et sans XML.

### 8-13. À quoi servent les annotations suivantes :
#### @Configuration
Indique qu'une classe contient des définitions de beans Spring. Cette classe peut contenir des méthodes annotées @Bean qui retournent des objets gérés par Spring.
#### @ComponentScan
Indique à Spring quels packages scanner pour détecter automatiquement les beans annotés avec @Component, @Service, @Repository, @Controller.
#### @Bean
Déclare un bean manuellement dans une classe @Configuration. Utilisé pour configurer des objets tiers (bibliothèques externes) ou des beans nécessitant une configuration complexe.
@Component, @Service, @Repository, @Controller
Annotations stéréotypées qui marquent une classe comme bean Spring :

- @Component : Bean générique
- @Service : Bean de la couche métier (logique applicative)
- @Repository : Bean de la couche persistance (accès aux données)
- @Controller : Bean de la couche présentation (contrôleur MVC)
- @RestController : Combinaison de @Controller et @ResponseBody pour les API REST

Hiérarchie : @Component est l'annotation parent, les autres en héritent.
#### @Autowired
Indique à Spring d'injecter automatiquement une dépendance. Peut être utilisé sur un constructeur, un setter ou un champ.
Types d'injection :

Par constructeur (recommandé) : dépendances immutables et obligatoires
Par setter : dépendances optionnelles
Par champ (déconseillé) : difficile à tester

#### @Qualifier
Spécifie quel bean injecter lorsque plusieurs implémentations d'une même interface existent. Utilisé en combinaison avec @Autowired pour résoudre l'ambiguïté.

### 14. Comment Spring détecte et crée automatiquement les composants ?
Processus de détection :

- @ComponentScan spécifie les packages à scanner
- Spring parcourt les classes dans ces packages
- Détecte les annotations : @Component, @Service, @Repository, @Controller
- Crée une instance de chaque classe annotée
- Analyse les dépendances (constructeur, setter, champ)
- Injecte les dépendances
- Appelle @PostConstruct si présent
- Bean prêt à être utilisé

### 15. Quelles sont les étapes du cycle de vie d'un bean ?

- Instantiation : Spring crée l'instance via le constructeur
- DI : Spring injecte les dépendances
- setBeanName() si la classe implémente BeanNameAware
- setBeanFactory() si la classe implémente BeanFactoryAware
- setApplicationContext() si la classe implémente ApplicationContextAware
- @PostConstruct ou afterPropertiesSet() : Initialisation personnalisée
- Bean prêt à être utilisé
- @PreDestroy ou destroy() : Nettoyage avant destruction
- Bean détruit

### 16. Quelle est la différence entre les scopes de bean (singleton, prototype, etc.) ?
- singleton (défaut) : Une seule instance pour toute l'application. Partagée entre tous les composants. Usage : Services, Repositories.
- prototype : Nouvelle instance créée à chaque injection ou récupération. Usage : Objets temporaires, non partagés.
- request : Une instance par requête HTTP. Scope valide uniquement dans une application web.
- session : Une instance par session HTTP utilisateur. Scope valide uniquement dans une application web.
- application : Une instance par ServletContext. Scope valide uniquement dans une application web.

### 17. Pourquoi la configuration manuelle (avant Spring Boot) est-elle importante à comprendre ?
Raisons pédagogiques :

- Comprendre les fondamentaux : Spring Boot masque la configuration automatique
- Débogage : savoir ce que fait Spring Boot en arrière-plan
- Personnalisation : pouvoir ajuster la configuration si nécessaire
- Entretiens techniques : souvent demandé en entretien d'embauche
- Projets legacy : de nombreuses applications n'utilisent pas Spring Boot
- Apprécier Spring Boot : mieux comprendre ce qu'il automatise

Sans Spring Boot, on configure manuellement DataSource, EntityManagerFactory, TransactionManager, DispatcherServlet, MessageConverters, etc. Avec Spring Boot, tout est auto-configuré via l'annotation @SpringBootApplication.

## SPRING DATA JPA - Persistance & Transactions

### 18. Qu'est-ce que Spring Data JPA et quel problème résout-il ?
Spring Data JPA est une abstraction au-dessus de JPA qui simplifie l'accès aux données. Il génère automatiquement les implémentations des repositories à partir d'interfaces.
Problème résolu :
Avant Spring Data JPA, il fallait écrire manuellement tout le code de persistance (méthodes CRUD, requêtes, gestion de l'EntityManager). Avec Spring Data JPA, on déclare simplement une interface et Spring génère automatiquement le code d'implémentation.
Avantages :

Réduit le code boilerplate de 90%
Génère automatiquement les requêtes SQL
Support natif de la pagination et du tri
Moins d'erreurs grâce à la génération automatique

### 19. Quelle est la différence entre JPA et Hibernate ?
JPA (Jakarta Persistence API) : Spécification standard Java EE qui définit les interfaces et annotations pour la persistance objet-relationnel. C'est une API, pas une implémentation.
Hibernate : Implémentation concrète de la spécification JPA. C'est l'ORM (Object-Relational Mapping) qui fait réellement le travail de persistance.
Autres implémentations de JPA : EclipseLink, OpenJPA, DataNucleus.
Dans un projet, on utilise les annotations JPA (@Entity, @Id, @Column) mais c'est Hibernate qui exécute les opérations en base de données.

### 20. Qu'est-ce qu'une entité JPA ?
Une entité JPA est une classe Java qui représente une table de base de données. C'est le concept central du mapping objet-relationnel (ORM).
Caractéristiques :

Annotée avec @Entity
Possède une clé primaire annotée @Id
Chaque champ correspond à une colonne de table
Peut avoir des relations avec d'autres entités (@OneToMany, @ManyToOne, etc.)

L'entité permet de manipuler des objets Java plutôt que d'écrire du SQL directement.

### 21. À quoi sert le DataSource ?
Le DataSource est l'objet qui gère la connexion à la base de données.
Rôles :

Configure le driver JDBC (ex: PostgreSQL, MySQL)
Définit l'URL de connexion à la base
Fournit les identifiants (username/password)
Gère le pool de connexions pour optimiser les performances

C'est le point d'entrée pour toute communication avec la base de données. Sans DataSource correctement configuré, l'application ne peut pas accéder aux données.

### 22. Que fait l'EntityManager ?
L'EntityManager est l'interface JPA centrale qui gère les opérations CRUD sur les entités.
Rôles :

persist : insérer une nouvelle entité en base
find : récupérer une entité par sa clé primaire
merge : mettre à jour une entité existante
remove : supprimer une entité
Exécuter des requêtes JPQL (Java Persistence Query Language)

Avec Spring Data JPA, on n'utilise généralement pas directement l'EntityManager car Spring le gère automatiquement via les repositories.

### 23. Quelle est la responsabilité du TransactionManager ?
Le TransactionManager gère les transactions de base de données, garantissant l'intégrité des données.
Rôles :

Démarre une transaction au début d'une opération
Commit si toutes les opérations réussissent
Rollback si une exception survient
Coordonne plusieurs opérations dans une seule transaction atomique

Le TransactionManager assure que les opérations en base sont soit toutes réussies, soit toutes annulées (principe ACID : Atomicité, Cohérence, Isolation, Durabilité).

### 24. À quoi sert l'annotation @EnableJpaRepositories ?
@EnableJpaRepositories active Spring Data JPA et indique à Spring où chercher les interfaces de repository.
Rôles :

Active le mécanisme de génération automatique des repositories
Scanne les packages spécifiés pour trouver les interfaces JpaRepository
Génère automatiquement les implémentations à l'exécution

Sans cette annotation, Spring ne détecterait pas les repositories et ne générerait pas leur code.

### 25. Qu'est-ce qu'un repository Spring Data ?
Un repository est une interface qui fournit des méthodes pour accéder aux données, sans qu'il soit nécessaire d'écrire le code d'implémentation.
Types de repositories :

Repository : Interface de base (marqueur)
CrudRepository : Méthodes CRUD de base
PagingAndSortingRepository : Ajoute pagination et tri
JpaRepository : Le plus complet, avec opérations batch (recommandé)

Spring Data génère automatiquement l'implémentation à partir du nom de l'interface et des méthodes déclarées.

### 26. Quelles sont les méthodes génériques fournies par JpaRepository ?
JpaRepository fournit automatiquement :
CRUD de base :

save : créer ou mettre à jour
saveAll : sauvegarder plusieurs entités
findById : récupérer par ID
findAll : récupérer toutes les entités
existsById : vérifier l'existence
count : compter le nombre d'entités
deleteById : supprimer par ID
delete : supprimer une entité
deleteAll : supprimer toutes les entités

Pagination et tri :

findAll(Pageable) : récupérer avec pagination
findAll(Sort) : récupérer avec tri

Opérations batch :

flush : forcer la synchronisation avec la base
saveAndFlush : sauvegarder et synchroniser immédiatement
deleteInBatch : supprimer plusieurs entités en une requête
deleteAllInBatch : supprimer toutes les entités en une requête

### 27. Comment gérer les transactions avec Spring (annotation, propagation, rollback, etc.) ?
Gestion des transactions avec @Transactional :
Déclaration :

Sur une classe : toutes les méthodes publiques deviennent transactionnelles
Sur une méthode : seule cette méthode est transactionnelle

Options principales :
Propagation : définit le comportement transactionnel

REQUIRED (défaut) : utilise la transaction existante ou en crée une
REQUIRES_NEW : crée toujours une nouvelle transaction
NESTED : transaction imbriquée
SUPPORTS : utilise la transaction si présente
NOT_SUPPORTED : s'exécute sans transaction
NEVER : lève une exception si une transaction existe
MANDATORY : lève une exception si aucune transaction n'existe

Isolation : niveau d'isolation des données

DEFAULT : niveau par défaut de la base de données
READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE

Rollback :

rollbackFor : spécifie les exceptions qui déclenchent un rollback
noRollbackFor : spécifie les exceptions qui n'entraînent pas de rollback
Par défaut : rollback uniquement sur RuntimeException et Error

Autres options :

readOnly : optimisation pour les opérations de lecture seule
timeout : durée maximale de la transaction en secondes

Comportement :
Transaction démarre automatiquement, commit si succès, rollback si exception RuntimeException.

### 28. Pourquoi définir manuellement la connexion à la base de données avant Spring Boot ?
Raisons :

Compréhension : savoir exactement comment Spring se connecte à la base
Contrôle : configuration fine de tous les paramètres de connexion
Débogage : identifier et résoudre les problèmes de connexion
Projets legacy : beaucoup d'applications n'utilisent pas Spring Boot
Personnalisation : ajuster le pool de connexions, le timeout, etc.
Pédagogie : comprendre ce que Spring Boot fait automatiquement

Avec Spring Boot, la configuration est automatique via application.properties. Sans Spring Boot, il faut configurer manuellement DataSource, EntityManagerFactory, et TransactionManager dans une classe @Configuration.

### 29. Que doit contenir une configuration de persistance complète (DataSource, EntityManagerFactory, TransactionManager) ?
Une configuration de persistance complète nécessite :

DataSource :


Driver JDBC (ex: org.postgresql.Driver)
URL de connexion (ex: jdbc:postgresql://localhost:5432/dbname)
Username et password


EntityManagerFactory :


Référence au DataSource
Packages à scanner pour les entités (@Entity)
JpaVendorAdapter (Hibernate)
Propriétés JPA/Hibernate (dialecte, DDL, logs)


TransactionManager :


Référence à l'EntityManagerFactory
Gestion des commits et rollbacks


Annotations d'activation :


@EnableJpaRepositories : active Spring Data JPA
@EnableTransactionManagement : active @Transactional

Cette configuration manuelle remplace l'auto-configuration de Spring Boot.

### 30. Qu'est-ce que la validation de contrainte dans le modèle (ex. email unique, longueur, etc.) ?
La validation de contrainte garantit l'intégrité des données avant leur persistance en base.
Types de contraintes :
Contraintes JPA (niveau base de données) :

@Column(nullable = false) : champ obligatoire
@Column(unique = true) : valeur unique
@Column(length = 100) : longueur maximale

Contraintes Jakarta Validation (niveau application) :

@NotNull : valeur non nulle
@NotBlank : chaîne non vide et non nulle
@Size(min, max) : longueur entre min et max
@Email : format email valide
@Min, @Max : valeurs numériques
@Pattern : expression régulière
@Valid : validation en cascade

Les contraintes JPA sont appliquées au niveau de la base de données (DDL). Les contraintes Jakarta Validation sont vérifiées avant la persistance, déclenchées par @Valid dans les contrôleurs.

### 31. Quelle est la différence entre une suppression logique (soft delete) et une suppression physique ?

#### Suppression physique (hard delete) :

L'enregistrement est définitivement supprimé de la base de données
Opération SQL : DELETE FROM table WHERE id = ?
Données perdues irrémédiablement
Utilisée quand les données ne doivent jamais être récupérées

#### Suppression logique (soft delete) :

L'enregistrement reste en base mais est marqué comme inactif
Ajout d'un champ booléen (ex: active, deleted) ou date de suppression
Opération SQL : UPDATE table SET active = false WHERE id = ?
Données conservées et récupérables si nécessaire
Avantages : historique, audit, récupération possible
Inconvénient : accumulation de données

Implémentation soft delete :

Ajouter un champ active (boolean) ou deletedAt (timestamp) dans l'entité
Modifier les requêtes pour filtrer les enregistrements actifs
Méthode delete() met à jour le flag au lieu de supprimer

Le soft delete est recommandé pour les données critiques ou nécessitant un audit.

## SPRING MVC - Contrôleurs & Couche Web

### 32. Que signifie MVC (Model-View-Controller) et quel est son objectif dans Spring ?

MVC est un pattern architectural qui sépare une application en trois composants :
Model : Représente les données et la logique métier (entités, services, repositories)
View : Présentation des données à l'utilisateur (pages HTML, JSON pour les API REST)
Controller : Gère les requêtes utilisateur, coordonne Model et View
Objectif dans Spring :

Séparation des responsabilités (Separation of Concerns)
Code plus maintenable et testable
Réutilisabilité des composants
Facilite le travail en équipe

Dans une API REST (notre cas) :

Model : entités User, UserService, UserRepository
View : JSON (sérialisation automatique)
Controller : UserController avec méthodes annotées @GetMapping, @PostMapping

### 33. Quel est le rôle du DispatcherServlet dans Spring MVC ?

Le DispatcherServlet est le contrôleur frontal (Front Controller) de Spring MVC. C'est le point d'entrée unique pour toutes les requêtes HTTP.
Rôles :

Intercepte toutes les requêtes HTTP entrantes
Consulte le HandlerMapping pour trouver le contrôleur approprié
Appelle le contrôleur via le HandlerAdapter
Reçoit le résultat du contrôleur
Utilise les MessageConverters pour sérialiser la réponse (JSON)
Renvoie la réponse HTTP au client

Sans DispatcherServlet, il faudrait gérer manuellement le routage de chaque URL vers chaque contrôleur. Le DispatcherServlet centralise et automatise ce processus.

### 34. Quelle est la différence entre un Controller et un RestController ?

@Controller :

Utilisé pour les applications web traditionnelles (pages HTML)
Les méthodes retournent le nom d'une vue (JSP, Thymeleaf)
Nécessite @ResponseBody sur chaque méthode pour retourner du JSON

@RestController :

Combinaison de @Controller et @ResponseBody
Utilisé pour les API REST
Les méthodes retournent directement des objets (sérialisés automatiquement en JSON)
Plus concis pour les services web RESTful

Pour une API REST, @RestController est toujours préféré car il évite de répéter @ResponseBody sur chaque méthode.

### 35-39. Quelle est la fonction des annotations suivantes :
@RequestMapping
Mappe une URL à une méthode de contrôleur. Peut spécifier la méthode HTTP (GET, POST, PUT, DELETE).
Peut être utilisée au niveau de la classe (préfixe commun) ou de la méthode (route spécifique).
@GetMapping, @PostMapping, @PutMapping, @DeleteMapping
Annotations spécialisées dérivées de @RequestMapping pour chaque méthode HTTP :

@GetMapping : requêtes GET (récupération de données)
@PostMapping : requêtes POST (création de ressources)
@PutMapping : requêtes PUT (mise à jour complète)
@DeleteMapping : requêtes DELETE (suppression)

Plus lisibles et concises que @RequestMapping(method = RequestMethod.GET).
@Valid
Déclenche la validation Jakarta Bean Validation sur un objet. Utilisé sur un paramètre de méthode (généralement avec @RequestBody).
Si la validation échoue, Spring lève une MethodArgumentNotValidException qui peut être interceptée par un @RestControllerAdvice.
@RequestBody
Indique que le paramètre doit être désérialisé depuis le corps de la requête HTTP (généralement du JSON).
Spring utilise Jackson pour convertir automatiquement le JSON en objet Java.
@PathVariable
Extrait une valeur depuis l'URL et l'injecte dans un paramètre de méthode.
Utilisé pour les paramètres dynamiques dans l'URL (ex: /users/{id} où {id} est un PathVariable).

### 40. Comment le DispatcherServlet traite-t-il une requête HTTP du début à la fin ?
Flux complet de traitement :

Client envoie une requête HTTP (ex: POST /api/users)
Serveur Tomcat reçoit la requête
DispatcherServlet intercepte la requête (mapping "/")
DispatcherServlet consulte le HandlerMapping
HandlerMapping trouve le contrôleur correspondant (UserController.createUser)
DispatcherServlet utilise le HandlerAdapter pour appeler la méthode
HandlerAdapter désérialise le JSON en objet Java (@RequestBody)
HandlerAdapter valide l'objet si @Valid est présent
La méthode du contrôleur s'exécute
Le contrôleur appelle le service (logique métier)
Le service appelle le repository (persistance)
La méthode retourne un objet de réponse
MessageConverter (Jackson) sérialise l'objet en JSON
DispatcherServlet construit la réponse HTTP
La réponse est renvoyée au client

Composants clés : DispatcherServlet, HandlerMapping, HandlerAdapter, MessageConverter.

### 41. Qu'est-ce que la classe de configuration Web (WebConfig) et à quoi sert-elle ?
WebConfig est une classe annotée @Configuration qui configure Spring MVC.
Contenu typique :

@EnableWebMvc : Active la configuration MVC par défaut
@ComponentScan : Indique les packages à scanner pour les contrôleurs
Configuration Jackson (ObjectMapper) :


Support des types Java 8 (LocalDateTime)
Format de sérialisation des dates


Configuration des MessageConverters :


Conversion Java vers JSON et inversement


Configuration de la validation :


LocalValidatorFactoryBean pour Jakarta Validation
MethodValidationPostProcessor pour valider les paramètres


Configuration CORS (si nécessaire) :


Autoriser les requêtes cross-origin

WebConfig remplace la configuration XML traditionnelle de Spring MVC.

### 42. Pourquoi faut-il initialiser le DispatcherServlet manuellement avant Spring Boot ?
Avant Spring Boot :

Pas d'auto-configuration
Le DispatcherServlet n'est pas créé automatiquement
Il faut le déclarer et le configurer manuellement dans WebAppInitializer
Il faut spécifier le mapping des URLs
Il faut définir le contexte Spring MVC

Raisons de le faire manuellement :

Compréhension : savoir comment fonctionne Spring MVC en profondeur
Contrôle : configuration fine du servlet (timeout, mapping)
Projets legacy : beaucoup n'utilisent pas Spring Boot
Débogage : identifier les problèmes de configuration

Avec Spring Boot, le DispatcherServlet est auto-configuré via @SpringBootApplication et les propriétés application.properties.

### 43. Qu'est-ce qu'un WebAppInitializer et pourquoi remplace-t-il web.xml ?
WebAppInitializer est une classe Java qui implémente l'interface WebApplicationInitializer. Elle remplace le fichier XML web.xml traditionnel.
Rôles :

Initialisation programmatique de l'application web
Création du contexte Spring (root et web)
Enregistrement du DispatcherServlet auprès de Tomcat
Configuration du mapping des URLs
Configuration des listeners et filtres si nécessaire

Avantages sur web.xml :

Configuration en Java (type-safe)
Pas de XML verbeux
Vérification à la compilation
Facilite le refactoring

Servlet 3.0+ permet l'initialisation sans web.xml grâce à l'interface ServletContainerInitializer, exploitée par Spring via WebApplicationInitializer.

### 44. Quelles sont les étapes de traitement d'une requête REST dans Spring MVC ?
Étapes détaillées :

Requête HTTP arrive au serveur Tomcat
DispatcherServlet intercepte la requête
HandlerMapping trouve le contrôleur et la méthode correspondants
HandlerInterceptor (si configuré) pré-traite la requête
HandlerAdapter prépare les arguments de la méthode :

@RequestBody : désérialisation JSON vers objet Java
@PathVariable : extraction des variables d'URL
@RequestParam : extraction des paramètres de requête
@Valid : validation des données


La méthode du contrôleur est invoquée
Logique métier exécutée (appel du service)
La méthode retourne un objet ou ResponseEntity
HandlerInterceptor (si configuré) post-traite la réponse
MessageConverter sérialise l'objet en JSON
DispatcherServlet construit la réponse HTTP complète (headers, status, body)
La réponse est envoyée au client

Chaque étape peut être personnalisée ou interceptée pour ajouter de la logique transversale (logging, sécurité, etc.).

### 45. Comment se fait la sérialisation et désérialisation des objets JSON ?
Spring utilise Jackson pour la conversion automatique JSON vers Java.
Désérialisation (JSON vers Java) :

Client envoie du JSON dans le corps de la requête
@RequestBody sur le paramètre de la méthode
MappingJackson2HttpMessageConverter intercepte
Jackson lit le JSON et crée l'objet Java correspondant
Si @Valid est présent, validation des contraintes
L'objet Java est passé à la méthode du contrôleur

Sérialisation (Java vers JSON) :

Méthode du contrôleur retourne un objet Java
@RestController ou @ResponseBody sur la méthode
MappingJackson2HttpMessageConverter intercepte
Jackson convertit l'objet Java en JSON
Le JSON est écrit dans le corps de la réponse HTTP
Le client reçoit le JSON

Configuration Jackson :

ObjectMapper bean configuré dans WebConfig
JavaTimeModule pour supporter LocalDateTime
Options de formatage (dates, null, etc.)

Jackson utilise la réflexion pour lire les getters/setters et mapper les propriétés JSON vers les champs Java.

### 46. À quoi sert un @RestControllerAdvice ?
@RestControllerAdvice est une annotation qui permet de gérer globalement les exceptions dans tous les contrôleurs REST.
Rôles :

Centraliser la gestion des erreurs
Éviter la duplication de code (try-catch dans chaque contrôleur)
Retourner des réponses d'erreur cohérentes et formatées
Gérer différents types d'exceptions de manière spécifique

Méthodes annotées @ExceptionHandler :

Chaque méthode gère un type d'exception spécifique
Retourne une réponse HTTP avec status et message appropriés

Avantages :

Code plus propre dans les contrôleurs
Gestion uniforme des erreurs
Facilite le logging des exceptions
Meilleure expérience utilisateur (messages d'erreur clairs)

Combinaison avec @ResponseStatus pour définir le code HTTP retourné.

### 47. Quelles sont les bonnes pratiques pour organiser les packages d'un projet MVC ?
Organisation recommandée par couches :
config : Classes de configuration Spring

PersistenceConfig : configuration JPA,
DataSource, TransactionManager

WebConfig : configuration MVC, Jackson, validation
WebAppInitializer : initialisation de l'application

controller : Contrôleurs REST

Un contrôleur par ressource (UserController, ProductController)
Annotations @RestController, @RequestMapping
Gestion des requêtes HTTP

service : Logique métier

Interfaces des services (UserService)
Implémentations dans un sous-package impl (UserServiceImpl)
Annotations @Service, @Transactional
Validation des règles métier

repository : Accès aux données

Interfaces Spring Data JPA
Annotation @Repository
Méthodes de requête dérivées

domain ou entity : Entités JPA

Classes annotées @Entity
Représentent les tables de base de données
Enums et types de domaine

dto : Data Transfer Objects

Objets pour transférer les données entre les couches
Séparation entre entité et exposition externe
DTOs de création, mise à jour, réponse

mapper : Conversions entre entités et DTOs

Classes annotées @Component
Méthodes de mapping (toEntity, toDTO)
Évite la logique métier dans les contrôleurs

exception : Gestion des erreurs

Exceptions personnalisées (ResourceNotFoundException, DuplicateEmailException)
GlobalExceptionHandler avec @RestControllerAdvice

Principes d'organisation :

Séparation claire des responsabilités
Chaque package a un rôle bien défini
Facilite la navigation dans le code
Permet de tester chaque couche indépendamment
Respecte l'architecture en couches

Architecture typique :
Controller appelle Service appelle Repository appelle Base de données
Les dépendances vont toujours de haut en bas :
Controller dépend de Service, Service dépend de Repository, Repository dépend de Domain
Ne jamais faire dépendre une couche inférieure d'une couche supérieure (ex: Service ne doit pas dépendre de Controller).