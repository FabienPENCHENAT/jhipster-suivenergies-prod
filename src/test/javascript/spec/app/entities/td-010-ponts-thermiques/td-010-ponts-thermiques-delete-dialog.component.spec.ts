import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SuivEnergiesTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { TD010PontsThermiquesDeleteDialogComponent } from 'app/entities/td-010-ponts-thermiques/td-010-ponts-thermiques-delete-dialog.component';
import { TD010PontsThermiquesService } from 'app/entities/td-010-ponts-thermiques/td-010-ponts-thermiques.service';

describe('Component Tests', () => {
  describe('TD010PontsThermiques Management Delete Component', () => {
    let comp: TD010PontsThermiquesDeleteDialogComponent;
    let fixture: ComponentFixture<TD010PontsThermiquesDeleteDialogComponent>;
    let service: TD010PontsThermiquesService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD010PontsThermiquesDeleteDialogComponent],
      })
        .overrideTemplate(TD010PontsThermiquesDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD010PontsThermiquesDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD010PontsThermiquesService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
