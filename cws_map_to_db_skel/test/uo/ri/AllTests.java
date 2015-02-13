package uo.ri;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	AssociationsTest.class,
	DomainModelTest.class,
	PersistenceTest.class
})
public class AllTests { }
