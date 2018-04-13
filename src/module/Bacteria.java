package module;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="Bacteria")
@XmlAccessorType (XmlAccessType.FIELD)
public class Bacteria {

	private String alphaGene, betaGene, gammeGene, flagella, toughness;
	
	@XmlElement(name = "Genotype")
	private String genotype;
	
	@XmlElement(name = "Classification")
	private String classification;
	
	public String getGenotyp() {
		return genotype;
	}

	public void setGenotyp(String genotype) {
		this.genotype = genotype;
	}

	public String getAlphaGene() {
		return alphaGene;
	}

	public void setAlphaGene(String alphaGene) {
		this.alphaGene = alphaGene;
	}

	public String getBetaGene() {
		return betaGene;
	}

	public void setBetaGene(String betaGene) {
		this.betaGene = betaGene;
	}

	public String getGammeGene() {
		return gammeGene;
	}

	public void setGammeGene(String gammeGene) {
		this.gammeGene = gammeGene;
	}

	public String getFlagella() {
		return flagella;
	}

	public void setFlagella(String flagella) {
		this.flagella = flagella;
	}

	public String getToughness() {
		return toughness;
	}

	public void setToughness(String toughness) {
		this.toughness = toughness;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}
}
