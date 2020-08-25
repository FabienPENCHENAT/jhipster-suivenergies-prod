import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TD007ParoiOpaqueService } from 'app/entities/td-007-paroi-opaque/td-007-paroi-opaque.service';
import { ITD007ParoiOpaque, TD007ParoiOpaque } from 'app/shared/model/td-007-paroi-opaque.model';

describe('Service Tests', () => {
  describe('TD007ParoiOpaque Service', () => {
    let injector: TestBed;
    let service: TD007ParoiOpaqueService;
    let httpMock: HttpTestingController;
    let elemDefault: ITD007ParoiOpaque;
    let expectedResult: ITD007ParoiOpaque | ITD007ParoiOpaque[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TD007ParoiOpaqueService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TD007ParoiOpaque(0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TD007ParoiOpaque', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TD007ParoiOpaque()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TD007ParoiOpaque', () => {
        const returnedFromService = Object.assign(
          {
            tr014TypeParoisOpaque: 'BBBBBB',
            reference: 'BBBBBB',
            deperditionThermique: 1,
            coefficientTransmissionThermiqueParoi: 1,
            resistanceThermiqueIsolation: 1,
            epaisseurIsolation: 1,
            surfaceParoi: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TD007ParoiOpaque', () => {
        const returnedFromService = Object.assign(
          {
            tr014TypeParoisOpaque: 'BBBBBB',
            reference: 'BBBBBB',
            deperditionThermique: 1,
            coefficientTransmissionThermiqueParoi: 1,
            resistanceThermiqueIsolation: 1,
            epaisseurIsolation: 1,
            surfaceParoi: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TD007ParoiOpaque', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
