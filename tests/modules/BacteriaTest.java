package modules;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import module.Bacteria;

class BacteriaTest {
	
	String genotype = "123456";
	Bacteria testBacteria = new Bacteria();
	
	@Test
	public void testCalculatingAlphaGene() {
		String alphaGene = testBacteria.calculateAlpha(genotype);
		assertEquals("16", alphaGene);
	}
	
	@Test
	public void testCalculatingBetaGene() {
		String alphaGene = testBacteria.calculateBeta(genotype);
		assertEquals("25", alphaGene);
	}
	
	@Test
	public void testCalculatingGammaGene() {
		String alphaGene = testBacteria.calculateGamma(genotype);
		assertEquals("34", alphaGene);
	}

}
