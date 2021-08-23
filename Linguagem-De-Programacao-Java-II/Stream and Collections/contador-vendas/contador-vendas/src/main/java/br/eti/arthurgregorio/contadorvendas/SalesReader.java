package br.eti.arthurgregorio.contadorvendas;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvToBeanBuilder;

public class SalesReader {

    private final List<Sale> sales;
	private int position;

    public SalesReader(String salesFile) {

        final var dataStream = ClassLoader.getSystemResourceAsStream(salesFile);        

        if (dataStream == null) {
            throw new IllegalStateException("File not found or is empty");
        }

        final var builder = new CsvToBeanBuilder<Sale>(new InputStreamReader(dataStream, StandardCharsets.ISO_8859_1));

        sales = builder
                .withType(Sale.class)
                .withSeparator(';')
                .build()
                .parse();
    }

    public void totalCompletedSales() {
        // TODO mostrar o total (em R$) de vendas completas
    	
    	final var total = sales.stream()
    			.filter(listSales -> listSales.getStatus().equals("Concluída"))
    			.map(Sale::getValue)
    			.reduce(BigDecimal.ZERO, BigDecimal::add);    			
    	
    	System.out.printf("O valor total de vendas completas foram de R$%s", toCurrency(total)).println();
    	System.out.println("-----------------------------------------------------------------------------");
    }

    public void totalCancelledSales() {
        // TODO mostrar o total (em R$) de vendas canceladas
    	final var total = sales.stream()
    			.filter(listSales -> listSales.getStatus().equals("Cancelada"))
    			.map(Sale::getValue)
    			.reduce(BigDecimal.ZERO, BigDecimal::add);    			
    	
    	System.out.printf("O valor total de vendas canceladas foram de R$%s", toCurrency(total)).println();
    	System.out.println("-----------------------------------------------------------------------------");
    }

    public void mostRecentSale() {
        // TODO encontrar qual foi a data da primeira venda
    	
    	 sales.stream()
    			.map(Sale::getSaleDate)
    			.sorted()
    			.findFirst()
    			.ifPresent(firstSale -> System.out.printf("A data da primeira venda foi %s", firstSale).println());
    	 System.out.println("-----------------------------------------------------------------------------");
    }

    public void daysBetweenFirstAndLast() {
        // TODO calcular qual a quantidade de dias entre a primeira e a ultima venda
    	    	    	
    	final var firstDateSale = sales.stream()
    			.map(Sale::getSaleDate)
    			.sorted()
    			.findFirst().get();
    			
    			
    	final var lastDateSale = sales.stream()
    			.map(Sale::getSaleDate)
    			.sorted(Comparator.reverseOrder())    			
    			.findFirst().get(); 
    	
    	final var calcDaysFirstLastSale = ChronoUnit.DAYS.between(firstDateSale, lastDateSale);
    	
    	System.out.printf("As quantidades de dias entre a primeira e última venda são de %s dias", calcDaysFirstLastSale ).println();
    	System.out.println("-----------------------------------------------------------------------------");
    	
    }

    public void totalSalesBySeller(String sellerName) {
        // TODO encontrar o total (em R$) de vendas do vendedor recebido por parametro
    	
    	final var totalPorVendedor = sales.stream()
    			.filter(listSales -> listSales.getStatus().equals("Concluída") && listSales.getSeller().equals(sellerName))
    			.map(Sale::getValue)
    			.reduce(BigDecimal.ZERO, BigDecimal::add); 			
    	
    	System.out.printf("O total de vendas do(a) vendedor(a) %s foram de R$%s", sellerName, toCurrency(totalPorVendedor)).println();
    	System.out.println("-----------------------------------------------------------------------------");
    	
    }

    public void countSalesByManager(String managerName) {
        // TODO contar a quantidade de vendas para o gerente recebido por parametro
    	
    	final var numeroDeVendas = sales.stream()
    			.filter(listSales -> listSales.getStatus().equals("Concluída") && listSales.getManager().equals(managerName))
    			.count();			
    	
    	System.out.printf("O número de vendas do(a) gerente(a) %s foram de %s", managerName, numeroDeVendas).println();
    	System.out.println("-----------------------------------------------------------------------------");
    	    	
    }

    public void totalSalesByMonth(Month... months) {
        // TODO totalizar o valor (em R$) de vendas para os meses informados por parametro    	
 	    	
    	final var totalSalesMonth = sales.stream()
    	.filter( listSales ->    	
    	Set.of(months).contains(listSales.getSaleDate().getMonth()) &&
    	listSales.getStatus().equals("Concluída"))    	
    	.map(Sale::getValue)
		.reduce(BigDecimal.ZERO, BigDecimal::add);  
    	
    	System.out.printf("O valor total de vendas nos meses %s foram de R$%s", Set.of(months), toCurrency(totalSalesMonth)).println();
    	System.out.println("-----------------------------------------------------------------------------");
    }

    public void rankingByDepartment() {
        // TODO faca um ranking contando o total (quantidade) de vendas por departamento
    	
    	
		System.out.println("Ranking dos totais de vendas por departamento");
		sales.stream().filter(listSales -> listSales.getStatus().equals("Concluída"))
				.collect(Collectors.groupingBy(Sale::getDepartment,
						Collectors.mapping(Sale::getValue, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))))
				.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.forEach(printValues -> System.out.println((this.position++) + "º - " +printValues.getKey() + " = R$" + toCurrency(printValues.getValue()))); 
		System.out.println("-----------------------------------------------------------------------------");
    	
    }

    public void rankingByPaymentMethod() {
        // TODO faca um ranking contando o total (quantidade) de vendas por meio de pagamento    	
    	this.position=1;
    	
    	System.out.println("Ranking dos totais de vendas por meio de pagamento");
		sales.stream().filter(listSales -> listSales.getStatus().equals("Concluída"))
				.collect(Collectors.groupingBy(Sale::getPaymentMethod,
						Collectors.mapping(Sale::getValue, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))))
				.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.forEach(printValues -> System.out.println((this.position++) + "º - " +printValues.getKey() + " = R$" + toCurrency(printValues.getValue())));
		System.out.println("-----------------------------------------------------------------------------");
    }

    public void bestSellers() {
        // TODO faca um top 3 dos vendedores que mais venderam (ranking por valor em vendas)
    	
		System.out.println("Ranking do top 3 vendedores que mais venderam");
		sales.stream().filter(listSales -> listSales.getStatus().equals("Concluída"))
				.collect(Collectors.groupingBy(Sale::getSeller,
						Collectors.mapping(Sale::getValue, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))))
				.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(3)
				.forEach(printValues -> System.out.println((this.position++) + "º - " +printValues.getKey() + " = R$" + toCurrency(printValues.getValue())));
    }

    /*
     * Use esse metodo para converter objetos BigDecimal para uma represetancao de moeda
     */
    private String toCurrency(BigDecimal value) {
        return NumberFormat.getInstance().format(value);
    }
}
