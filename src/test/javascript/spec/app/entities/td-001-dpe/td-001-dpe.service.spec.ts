import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { TD001DPEService } from 'app/entities/td-001-dpe/td-001-dpe.service';
import { ITD001DPE, TD001DPE } from 'app/shared/model/td-001-dpe.model';

describe('Service Tests', () => {
  describe('TD001DPE Service', () => {
    let injector: TestBed;
    let service: TD001DPEService;
    let httpMock: HttpTestingController;
    let elemDefault: ITD001DPE;
    let expectedResult: ITD001DPE | ITD001DPE[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TD001DPEService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TD001DPE(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateReceptionDpe: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TD001DPE', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateReceptionDpe: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReceptionDpe: currentDate,
          },
          returnedFromService
        );

        service.create(new TD001DPE()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TD001DPE', () => {
        const returnedFromService = Object.assign(
          {
            numeroDpe: 'BBBBBB',
            tr001ModeleDpe: 'BBBBBB',
            nomMethodeDpe: 'BBBBBB',
            consommationEnergie: 1,
            classeConsommationEnergie: 'BBBBBB',
            estimationGes: 1,
            classeEstimationGes: 'BBBBBB',
            tr002TypeBatiment: 'BBBBBB',
            anneeConstruction: 1,
            surfaceHabitable: 1,
            tv016Departement: 'BBBBBB',
            commune: 'BBBBBB',
            arrondissement: 'BBBBBB',
            typeVoie: 'BBBBBB',
            nomRue: 'BBBBBB',
            numeroRue: 'BBBBBB',
            batiment: 'BBBBBB',
            escalier: 'BBBBBB',
            etage: 'BBBBBB',
            porte: 'BBBBBB',
            codePostal: 'BBBBBB',
            codeInseeCommune: 'BBBBBB',
            codeInseeCommuneActualise: 'BBBBBB',
            codeInseeCommuneCorrige: 'BBBBBB',
            numeroLot: 'BBBBBB',
            dateReceptionDpe: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReceptionDpe: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TD001DPE', () => {
        const returnedFromService = Object.assign(
          {
            numeroDpe: 'BBBBBB',
            tr001ModeleDpe: 'BBBBBB',
            nomMethodeDpe: 'BBBBBB',
            consommationEnergie: 1,
            classeConsommationEnergie: 'BBBBBB',
            estimationGes: 1,
            classeEstimationGes: 'BBBBBB',
            tr002TypeBatiment: 'BBBBBB',
            anneeConstruction: 1,
            surfaceHabitable: 1,
            tv016Departement: 'BBBBBB',
            commune: 'BBBBBB',
            arrondissement: 'BBBBBB',
            typeVoie: 'BBBBBB',
            nomRue: 'BBBBBB',
            numeroRue: 'BBBBBB',
            batiment: 'BBBBBB',
            escalier: 'BBBBBB',
            etage: 'BBBBBB',
            porte: 'BBBBBB',
            codePostal: 'BBBBBB',
            codeInseeCommune: 'BBBBBB',
            codeInseeCommuneActualise: 'BBBBBB',
            codeInseeCommuneCorrige: 'BBBBBB',
            numeroLot: 'BBBBBB',
            dateReceptionDpe: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateReceptionDpe: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TD001DPE', () => {
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
