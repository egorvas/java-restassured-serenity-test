package steps;

import net.thucydides.core.annotations.Steps;

/**
 * Created by egorvas on 27.07.16.
 */

public class StepSets {

    @Steps
    AssertSteps assertSteps;

    @Steps
    CoinmarketcapSteps coinmarketcapSteps;

    @Steps
    EverexSteps everexSteps;

    @Steps
    EthplorerSteps ethplorerSteps;

    @Steps
    LogSteps logSteps;



    public CoinmarketcapSteps coinmarketcapSteps() {
        return coinmarketcapSteps;
    }

    public EverexSteps everexSteps() {
        return everexSteps;
    }

    public EthplorerSteps ethplorerSteps() {
        return ethplorerSteps;
    }

    public AssertSteps assertSteps() {
        return assertSteps;
    }

    public LogSteps logSteps() {
        return logSteps;
    }


}
