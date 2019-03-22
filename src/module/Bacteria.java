package module;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Bacteria")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bacteria {

	private String alphaGene, betaGene, gammeGene, flagella, toughness, number, rank;

	@XmlElement(name = "Genotype")
	private String genotype;

	@XmlElement(name = "Classification")
	private String classification;

	public Bacteria() {

	}

	public Bacteria(String genotype) {
		this.genotype = genotype;
	}

	public String calculateAlpha(String genotype) {
		return (isGenotypeLengthEqualsSix(genotype)) ?  this.alphaGene = genotype.substring(0, 1) + genotype.substring(5, 6) : "";
	}

	public String calculateBeta(String genotype) {
		return (isGenotypeLengthEqualsSix(genotype)) ?  this.betaGene = genotype.substring(1, 2) + genotype.substring(4, 5) : "";
	}

	public String calculateGamma(String genotype) {
		return (isGenotypeLengthEqualsSix(genotype)) ?  this.gammeGene = genotype.substring(2, 4) : "";
	}
	
	public boolean isGenotypeLengthEqualsSix(String genotype) {
		return (genotype.length() == 6) ? true :  false;
	}

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
		this.number = classification.substring(0, 1);
		this.rank = classification.substring(1, 2);
		this.classification = classification;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
}
