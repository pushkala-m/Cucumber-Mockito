package com.tech.diviso.cucumber.stepDefs;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
public class sumStepDefsIT {
    Integer num1;
    Integer num2;
    Integer sum;

    @Given("create controller")
    public void create_controller() {
        System.out.println("created calc");
    }

    @When("i give number {int} {int}")
    public void i_give_number(Integer int1, Integer int2) {

        System.out.println("numbers "+int1+"  "+int2);
        num1=int1;
        num2=int2;
        sum=num1+num2;
    }

    @Then("the result was {int}")
    public void the_result_was(Integer int1) {
        Assert.assertSame(int1,sum);
    }

}
