@smoke
Feature: Search flow

	Scenario: Search by Professor with only an available course
 		Given I search for "Por Professor"
		And I choose "Wagner Damazio" in the list
		When I select an available course
		Then I should see a right installment payment conditions


	Scenario: Search by Professor with many available courses
		Given I search for "Por Professor"
		And I choose "Equipe Stefan Fantini" in the list
		When I select an available course
		Then I should see a right installment payment conditions
