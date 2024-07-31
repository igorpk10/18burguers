package br.com.eighteenburguers.product.usecase;

import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.services.DeleteProductByIdService;
import br.com.eighteenburguers.product.services.FindProductByIdService;
import br.com.eighteenburguers.product.exceptions.ProductNotExistsException;

public class DeleteProductByIdUseCaseImpl implements br.com.eighteenburguers.product.usecase.DeleteProductByIdUseCase {

    private final DeleteProductByIdService deleteProductByIdService;

    private final FindProductByIdService findProductByIdService;

    public DeleteProductByIdUseCaseImpl(DeleteProductByIdService deleteProductByIdService, FindProductByIdService findProductByIdService) {
        this.deleteProductByIdService = deleteProductByIdService;
        this.findProductByIdService = findProductByIdService;
    }

    @Override
    public void delete(Long id) throws BusinessException {
        findProductByIdService.find(id).orElseThrow(ProductNotExistsException::new);
        deleteProductByIdService.delete(id);
    }
}
