package br.com.eighteenburguers.product.usecase;

import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.services.DeleteProductByIdService;
import br.com.eighteenburguers.product.services.FindProductByIdService;
import br.com.eighteenburguers.product.exceptions.ProductNotExistsException;

public class DeleteProductByIdUseCaseImpl implements br.com.eighteenburguers.product.usecase.DeleteProductByIdUseCase {

    private final DeleteProductByIdService deleteProductByIdOutputPort;

    private final FindProductByIdService findProductByIdOutputPort;

    public DeleteProductByIdUseCaseImpl(DeleteProductByIdService deleteProductByIdOutputPort, FindProductByIdService findProductByIdOutputPort) {
        this.deleteProductByIdOutputPort = deleteProductByIdOutputPort;
        this.findProductByIdOutputPort = findProductByIdOutputPort;
    }

    @Override
    public void delete(Long id) throws BusinessException {
        findProductByIdOutputPort.find(id).orElseThrow(ProductNotExistsException::new);
        deleteProductByIdOutputPort.delete(id);
    }
}
