package tb;

public class Barang {
	
	String SKU;
	String namaBarang;
	int stok;
	int hargaBeli;
	int hargaJual;
	
	public Barang(String sku, String namaBarang, Integer stock, Integer hargaBeli, Integer hargaJual) {
	   
	         SKU        = sku;
		this.namaBarang = namaBarang;
		this.stok       = stock;
		this.hargaBeli  = hargaBeli;
		this.hargaJual  = hargaJual;
	}

	public String getSKU() {
	return SKU;
	}
	
	public void setSKU(String sku) {
	SKU = sku;
	}
	
	public String getNamaBarang() {
	return namaBarang;
	}
	
	public void setNamaBarang(String namaBarang) {
	this.namaBarang = namaBarang;
	}
	
	public int getStok() {
	return stok;
	}
	
	public void setStok(int stock) {
	this.stok = stock;
	}
	
	public int getHargaBeli() {
	return hargaBeli;
	}
	
	public void setHargaBeli(int hargaBeli) {
	this.hargaBeli = hargaBeli;
	}
	
	public int getHargaJual() {
	return hargaJual;
	}
	
	public void setHargaJual(int hargaJual) {
	this.hargaJual = hargaJual;
	}
	
}
