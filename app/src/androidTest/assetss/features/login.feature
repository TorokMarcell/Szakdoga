Feature: bejelentkezés
    Bejelentkezik az adott email-cím jelszó párossal

  @login-feature
  Scenario: Input email and password
    Given létezik a login felület
    When beírom az emailem "xd@xd.com"
    And beírom a jelszavam "asdasd"
    And rámegyek a bejelentkezés gombra
    Then a validáció menüben találom magam

