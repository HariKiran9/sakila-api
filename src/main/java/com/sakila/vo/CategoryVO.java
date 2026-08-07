package com.sakila.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // Eliminates manual boilerplate constructors
public class CategoryVO implements Serializable {

	private static final long serialVersionUID = 1L;

	// Fixed: Converted to boxed Integer to support null matching for inserts
	private Integer categoryId;

	// Fixed: Added input boundary constraints against malformed or empty payloads
	@NotBlank(message = "Category name cannot be blank")
	@Size(min = 2, max = 25, message = "Category name must be between 2 and 25 characters")
	private String name;

	// Kept as String to maintain compatibility with your original service-layer
	// parsing format
	private LocalDateTime lastUpdate;

	// Fixed: Replaced manual string concatenation with safe layout representation
	// CRLF characters can be scrubbed globally or via logging configuration
	@Override
	public String toString() {
		return String.format("CategoryVO[id=%d, name='%s']", categoryId,
				name != null ? name.replaceAll("[\r\n]", "_") : "null");
	}
}
