Feature: bejelentkezés
    Bejelentkezik az adott email-cím jelszó párossal

  @login-feature
  Scenario Outline: Input email and password
    Given létezik a login felület
    When beírom az emailem "<email>"
    And beírom a jelszavam "<password>"
    And rámegyek a bejelentkezés gombra
    Then a validáció menüben találom magam

    Examples:
     |email| password |
     |xd@xd.com| asdasd|