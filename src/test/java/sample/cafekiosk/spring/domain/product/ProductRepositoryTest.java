package sample.cafekiosk.spring.domain.product;

import static org.assertj.core.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk.spring.domain.product.ProductType.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
// @SpringBootTest
@DataJpaTest // @SpringBootTest에 비해서 Jpa 관련 bean들만 사용하기 때문에 속도가 빠릅니다
class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@DisplayName("원하는 판매 상태를 가진 상품들을 조회한다.")
	@Test
	void findAllBySellingStatusIn() {
		// given
		Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
		Product product2 = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
		Product product3 = createProduct("003", HANDMADE, STOP_SELLING, "팥빙수", 7000);
		productRepository.saveAll(List.of(product1, product2, product3));

		// when
		List<Product> products = productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));

		// then
		assertThat(products).hasSize(2)
			// 테스트 하고자 하는 필드만 추출할 수 있다
			.extracting("productNumber", "name", "sellingStatus")
			// containsExactly: 순서까지 맞는지 확인해 준다
			// containsExactlyInAnyOrder: 순서 상관없이 확인해 준다.
			.containsExactlyInAnyOrder(
				tuple("001", "아메리카노", SELLING),
				tuple("002", "카페라떼", HOLD)
			);
	}

	@DisplayName("상품번호 리스트로 상품들을 조회한다.")
	@Test
	void findAllByProductNumberIn() {
		// given
		Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
		Product product2 = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
		Product product3 = createProduct("003", HANDMADE, STOP_SELLING, "팥빙수", 7000);
		productRepository.saveAll(List.of(product1, product2, product3));

		// when
		List<Product> products = productRepository.findAllByProductNumberIn(List.of("001", "002"));

		// then
		assertThat(products).hasSize(2)
			// 테스트 하고자 하는 필드만 추출할 수 있다
			.extracting("productNumber", "name", "sellingStatus")
			// containsExactly: 순서까지 맞는지 확인해 준다
			// containsExactlyInAnyOrder: 순서 상관없이 확인해 준다.
			.containsExactlyInAnyOrder(
				tuple("001", "아메리카노", SELLING),
				tuple("002", "카페라떼", HOLD)
			);
	}

	@DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어온다.")
	@Test
	void findLatestProductNumber() {
		// given
		String targetProductNumber = "003";

		Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
		Product product2 = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
		Product product3 = createProduct(targetProductNumber, HANDMADE, STOP_SELLING, "팥빙수", 7000);
		productRepository.saveAll(List.of(product1, product2, product3));

		// when
		String latestProductNumber = productRepository.findLatestProductNumber();

		// then
		assertThat(latestProductNumber).isEqualTo(targetProductNumber);
	}

	@DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어올 때, 상품이 하나도 없는 경우에는 null을 반환한다.")
	@Test
	void findLatestProductNumberWhenProductIsEmpty() {
		// when
		String latestProductNumber = productRepository.findLatestProductNumber();

		// then
		assertThat(latestProductNumber).isNull();
	}

	private Product createProduct(String productNumber, ProductType type, ProductSellingStatus sellingStatus,
		String name, int price) {
		return Product.builder()
			.productNumber(productNumber)
			.type(type)
			.sellingStatus(sellingStatus)
			.name(name)
			.price(price)
			.build();
	}

}
