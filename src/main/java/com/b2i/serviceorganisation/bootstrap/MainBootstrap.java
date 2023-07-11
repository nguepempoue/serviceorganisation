package com.b2i.serviceorganisation.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainBootstrap implements CommandLineRunner {

    private final RoleBootstrap roleBootstrap;

    private final ActuatorBootstrap actuatorBootstrap;

    private final AccountTypeBootstrap accountTypeBootstrap;

    private final AccountingClassBootstrap accountingClassBootstrap;

    private final MainOfficeBootstrap mainOfficeBootstrap;

    private final StatusBootstrap statusBootstrap;

    private final PieceTypeBootstrap pieceTypeBootstrap;

    private final CivilityBootstrap civilityBootstrap;

    private final FamilySituationBootstrap familySituationBootstrap;

    private final CountryBootstrap countryBootstrap;

    private final PostBootstrap postBootstrap;

    private final PassiveIncomeAccountBootstrap passiveIncomeAccountBootstrap;

    private final FunctionBootstrap functionBootstrap;

    private final UserTypeBootstrap userTypeBootstrap;

    private final UserCategoryBootstrap userCategoryBootstrap;


    public MainBootstrap(RoleBootstrap roleBootstrap, ActuatorBootstrap actuatorBootstrap, AccountTypeBootstrap accountTypeBootstrap, AccountingClassBootstrap accountingClassBootstrap, MainOfficeBootstrap mainOfficeBootstrap, StatusBootstrap statusBootstrap, PieceTypeBootstrap pieceTypeBootstrap, CivilityBootstrap civilityBootstrap, FamilySituationBootstrap familySituationBootstrap, CountryBootstrap countryBootstrap, PostBootstrap postBootstrap, PassiveIncomeAccountBootstrap passiveIncomeAccountBootstrap, FunctionBootstrap functionBootstrap, UserTypeBootstrap userTypeBootstrap, UserCategoryBootstrap userCategoryBootstrap) {
        this.roleBootstrap = roleBootstrap;
        this.actuatorBootstrap = actuatorBootstrap;
        this.accountTypeBootstrap = accountTypeBootstrap;
        this.accountingClassBootstrap = accountingClassBootstrap;
        this.mainOfficeBootstrap = mainOfficeBootstrap;
        this.statusBootstrap = statusBootstrap;
        this.pieceTypeBootstrap = pieceTypeBootstrap;
        this.civilityBootstrap = civilityBootstrap;
        this.familySituationBootstrap = familySituationBootstrap;
        this.countryBootstrap = countryBootstrap;
        this.postBootstrap = postBootstrap;
        this.passiveIncomeAccountBootstrap = passiveIncomeAccountBootstrap;
        this.functionBootstrap = functionBootstrap;
        this.userTypeBootstrap = userTypeBootstrap;
        this.userCategoryBootstrap = userCategoryBootstrap;
    }


    @Override
    public void run(String... args) throws Exception {

        roleBootstrap.seed();
        actuatorBootstrap.seed();
        accountTypeBootstrap.seed();
        accountingClassBootstrap.seed();
        postBootstrap.seed();
        mainOfficeBootstrap.seed();
        statusBootstrap.seed();
        civilityBootstrap.seed();
        pieceTypeBootstrap.seed();
        familySituationBootstrap.seed();
        countryBootstrap.seed();
        passiveIncomeAccountBootstrap.seed();
        functionBootstrap.seed();
        userTypeBootstrap.seed();
        userCategoryBootstrap.seed();
    }
}
