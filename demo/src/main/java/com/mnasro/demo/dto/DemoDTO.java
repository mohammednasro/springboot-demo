package com.mnasro.demo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull(message = "please enter valid fromCurrency")
	@Pattern(regexp = "^[A-Z]{3}$", message = "please enter valid fromCurrency")
	private String fromCurrency;
	@NotNull(message = "please enter valid fromCurrency")
	@Pattern(regexp = "^[A-Z]{3}$", message = "please enter valid toCurrency")
	private String toCurrency;
	@NotNull
	@DateTimeFormat
	@Past(message = "please enter valid timestamp")
	private Date timestamp;
	@NotNull
	@Positive
	@Digits(integer = Integer.MAX_VALUE, fraction = 0)
	private Double amount;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DemoDTO other = (DemoDTO) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(fromCurrency, other.fromCurrency)
				&& Objects.equals(timestamp, other.timestamp) && Objects.equals(toCurrency, other.toCurrency);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, fromCurrency, timestamp, toCurrency);
	}

}
